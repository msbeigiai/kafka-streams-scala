package streams

import io.circe.generic.auto.exportEncoder
import io.circe.{Decoder, Json, _}
import io.circe.parser._
import io.circe.syntax.EncoderOps
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.kstream.JoinWindows
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{KStream, KTable}
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.scala.serialization.Serdes.{longSerde, stringSerde}
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.Properties
import scala.util.Left


object KafkaStreams06 {

  object Topics {
    val NewPersonTopic = "db_modiseh.kafka_test.person4"
    val Person9Topic = "db_modiseh.kafka_test.person_nine"
    val FavoriteFoodTopic = "db_modiseh.kafka_test.favorite_food2"
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

    import domain._
    import jsonstrings.JsonStrings.JsonData._
    import Topics._
    import streams.domain.ClassesDomain._

    val builder = new StreamsBuilder()

    val personRawStream: KStream[String, String] = builder.stream[String, String](NewPersonTopic)

    val personStream = personRawStream.mapValues { v =>
      val valueJson = parse(v) match {
        case Left(ex) => throw new IllegalArgumentException(ex.message)
        case Right(json) => json
      }
      val personJson = (valueJson \\ "after").head.asJson.noSpaces
      val personDecoded = decode[Person](personJson) match {
        case Left(ex) => throw new IllegalArgumentException(ex.getMessage)
        case Right(person) => person
      }
      personDecoded
    }

    val favoriteFoodRawStream: KStream[String, String] = builder.stream[String, String](FavoriteFoodTopic)
    val favoriteFoodStream = favoriteFoodRawStream.mapValues { ff =>
      val valueJson = parse(ff) match {
        case Left(ex) => throw new IllegalArgumentException(ex)
        case Right(json) => json
      }
      val fFoodJson = ((valueJson \\ "payload").head \\ "after").head.asJson.noSpaces
      val fFDecoded = decode[FavoriteFood](fFoodJson) match {
        case Left(ex) => throw new IllegalArgumentException(ex.getMessage)
        case Right(favoriteFood) => favoriteFood
      }
      fFDecoded
    }


    personStream.to(Person9Topic)


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
