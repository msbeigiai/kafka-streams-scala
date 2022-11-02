package streams

import io.circe.generic.auto._
import io.circe.jawn.decode
import io.circe.parser._
import io.circe.syntax.{EncoderOps, _}
import io.circe.{Decoder, Encoder}
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.kstream.{GlobalKTable, JoinWindows}
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream.{KStream, KTable}
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.scala.serialization.Serdes._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.Properties


object KafkaStreams01 {

  object Domain {
    type UserId = String
    type Profile = String
    type Product = String
    type OrderId = String
    type Status = String

    case class Order(orderId: OrderId, userId: UserId, products: List[Product], amount: Double)
    case class Discount(profile: Profile, amount: Double)
    case class Payment(orderId: OrderId, status: Status)
  }

  object Topics {
    val OrdersByUser = "orders-by-user"
    val DiscountProfileByUser = "discount-profile-by-user"
    val Discounts = "discounts"
    val Orders = "orders"
    val Payments = "payments"
    val PaidOrders = "paid-orders"
  }

  import Domain._
  import Topics._

  implicit def serde[A >: Null : Decoder : Encoder]: Serde[A] = {
    val serializer = (a: A) => a.asJson.noSpaces.getBytes()
    val deserializer = (bytes: Array[Byte]) => {
      val string = new String(bytes)
      decode[A](string).toOption
    }
    Serdes.fromFn[A](serializer, deserializer)
  }

  def main(args: Array[OrderId]): Unit = {
    val builder = new StreamsBuilder()

    val usersOrdersStream: KStream[UserId, Order] = builder.stream[UserId, Order](OrdersByUser)

    val userProfilesTable: KTable[UserId, Profile] = builder.table[UserId, Profile](DiscountProfileByUser)

    val discountProfileGTable: GlobalKTable[Profile, Discount] = builder.globalTable[Profile, Discount](Discounts)

    val expensiveOrders: KStream[UserId, Order] = usersOrdersStream.filter((_, o) => o.amount > 1000)

    val listOfProducts: KStream[UserId, List[Product]] = usersOrdersStream.mapValues(_.products)

    val productsStream: KStream[UserId, Product] = usersOrdersStream.flatMapValues(_.products)

    val ordersWithUserProfiles: KStream[UserId, (Order, Profile)] = usersOrdersStream.join(userProfilesTable)(
      (o, p) => (o, p)
    )

    val discountedOrderStream = ordersWithUserProfiles.join(discountProfileGTable) (
      { case (userId, (order, profile)) => profile },
      { case ((order, profile), discount) => order.copy(amount = order.amount - discount.amount) }
    )

    val orderStream = discountedOrderStream.selectKey((u, o) => o.orderId)
    val paymentsStream = builder.stream[OrderId, Payment](Payments)

    val joinWindow = JoinWindows.of(Duration.of(5, ChronoUnit.MINUTES))

    val joinOrdersPayment = (order: Order, payment: Payment) =>
      if (payment.status == "PAID") Option(order) else Option.empty[Order]

    val ordersPaid = orderStream.join(paymentsStream)(joinOrdersPayment, joinWindow)
      .flatMapValues(maybeOrder => maybeOrder.toIterable)

    ordersPaid.to(PaidOrders)

    val topology = builder.build()

    val props = new Properties
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "orders-application")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.70.24:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)

    val application = new KafkaStreams(topology, props)

    application.start()

  }


}
