package streams

import io.circe.generic.auto.exportEncoder
import io.circe.{Json, _}
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

object KafkaStreams05 {

  object Topics {
    val PersonTopic = "db_modiseh.kafka_test.person"
    val Person2Topic = "test-person2"
    val Person3Topic = "test-person3"
    val Person4Topic = "test-person4"
    val CountryTopic = "filter-by-country"
    val FavoriteFoodTopic = "db_modiseh.kafka_test.favorite_food"
    val FavoriteFoodByPersonTopic = "favorite-food-by-person"
    val PersonByYearTopic = "person-by-year"
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

    val personRawStream: KStream[String, String] = builder.stream[String, String](PersonTopic)

    val personStream: KStream[Long, Person] = personRawStream.map { (k, v) =>
      val valueJson = parse(v) match {
        case Left(ex) => throw new IllegalArgumentException(ex.message)
        case Right(json) => json
      }
      val personJson = ((valueJson \\ "payload").head \\ "after").head.asJson.noSpaces
      val personDecoded = decode[Person](personJson) match {
        case Left(ex) => throw new IllegalArgumentException(ex.getMessage)
        case Right(person) => person
      }
      (personDecoded.personId, personDecoded)
    }

    val favoriteFoodRawStream: KStream[String, String] = builder.stream[String, String](FavoriteFoodTopic)
    val favoriteFoodStream: KStream[Long, FavoriteFood] = favoriteFoodRawStream.map { (id, ff) =>
      val valueJson = parse(ff) match {
        case Left(ex) => throw new IllegalArgumentException(ex)
        case Right(json) => json
      }
      val fFoodJson = ((valueJson \\ "payload").head \\ "after").head.asJson.noSpaces
      val fFDecoded = decode[FavoriteFood](fFoodJson) match {
        case Left(ex) => throw new IllegalArgumentException(ex.getMessage)
        case Right(favoriteFood) => favoriteFood
      }
      (fFDecoded.personId, fFDecoded)
    }

    personStream.foreach { (k, data) =>
      println("STREAMED +++++++++++ " + data)
    }

    personStream.to(Person4Topic)


    /*personStream.to(Person2Topic)

    personStream.filter { (id, person) =>
      person.personId > 3
    }.mapValues( v => v.asJson.noSpaces)
      .to(Person3Topic)

    personStream.filter { (_, person) =>
      person.birthdate.getYear > 1970
    }.mapValues(_.asJson.noSpaces)
      .to(PersonByYearTopic)*/


    /*val person = decode[Person](rawJsonString2)
    println(person)*/

    /*val kafkaJson = parse(rawJsonString) match {
      case Left(ex) => throw new IllegalArgumentException(ex.message)
      case Right(json) => json
    }

    val personJson = ((kafkaJson \\ "payload").head \\ "after").head.asJson.noSpaces

    val person2 = decode[Person](personJson)
    println(person2)*/



    /*val kafkaJsonTimestamp = parse(rawJsonTimestamp) match {
      case Left(ex) => throw new IllegalArgumentException(ex.message)
      case Right(json) => json
    }

    val personJsonTimestamp = ((kafkaJsonTimestamp \\ "payload").head \\ "after").head.asJson.noSpaces

    val person3 = decode[Person](personJsonTimestamp)
    println(person3)*/


    val topology = builder.build()

    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app-02")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.70.24:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)

    val application = new KafkaStreams(topology, props)
    application.start()
  }



}
