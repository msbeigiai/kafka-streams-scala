package streams.jsonstrings

import io.circe.generic.auto._
import io.circe.{Decoder, Encoder}
import io.circe.parser._
import io.circe.syntax.EncoderOps
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{KStream, KTable}
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

import java.util.Properties


object KafkaStreams07 {

  object Domain {
    type UserId = String
    type FoodId = String

    case class FavoriteFood(foodId: FoodId, food: String)
    case class User(userId: UserId, name: String)
  }

  object Topics {
    val UserTopic = "user"
    val FavoriteFoodTopic = "favorite-food"
      val UserAndFavoriteFoodTopic = "user-and-favorite-food"
    val UserOptionTopic = "user-option"
    val UserFilteredFavoriteFoodTopic = "user-filtered-favorite-food"
    val UserWithAllFavoriteFoods = "user-with-all-favorite-food"
  }

  implicit def serde[A >: Null : Decoder : Encoder]: Serde[A] = {
    val serializer = (a: A) => a.asJson.noSpaces.getBytes()
    val deserializer = (bytes: Array[Byte]) => {
      val string = new String(bytes)
      decode[A](string).toOption
    }
    Serdes.fromFn[A](serializer, deserializer)
  }

  def main(args: Array[String]): Unit = {
    import Domain._
    import Topics._

    val builder = new StreamsBuilder()

    val userStream: KStream[UserId, User] = builder.stream[UserId, User](UserTopic)
    val favoriteFoodTable: KTable[UserId, FavoriteFood] = builder.table(FavoriteFoodTopic)

    val joinedUsersFavoriteFoods: KStream[UserId, (User, FavoriteFood)] = userStream.join(favoriteFoodTable)( (u, f) =>
      (u, f)
    )

    joinedUsersFavoriteFoods.to(UserAndFavoriteFoodTopic)





    val topology = builder.build()
    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app-04")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.70.24:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)

    val application = new KafkaStreams(topology, props)
    application.start()
  }
}
