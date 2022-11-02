package streams


import io.circe.generic.codec.DerivedAsObjectCodec.deriveCodec
import io.circe.{Json, _}
import io.circe.parser._
import io.circe.syntax.EncoderOps
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.{KStream, KTable}
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

import java.time.LocalDate
import java.util.Properties

object KafkaStreams04 {

  object Domain {
    type PersonId = String
    type FoodId = String
    type JSONData = Json

    case class Person(person_id: PersonId, fname: String, lname: String, eye_color: String, birth_date: LocalDate,
                      street: String, city: String, state: String, country: String, postal_code: String)
    case class FavoriteFood(food: String)

  }

  object Topics {
    val PersonTopic = "db_modiseh.kafka_test.person"
    val Person3Topic = "test-person3"
    val CountryTopic = "filter-by-country"
    val FavoriteFoodTopic = "db_modiseh.kafka_test.favorite_food"
    val FavoriteFoodByPersonTopic = "favorite-food-by-person"
  }

   implicit def serde[A >: Null : Decoder : Encoder]: Serde[A] = {
     val serializer = (a: A) => a.asJson.noSpaces.getBytes()
     val deserializer = (bytes: Array[Byte]) => {
       val string = new String(bytes)
       decode[A](string).toOption
     }
     Serdes.fromFn[A](serializer, deserializer)
   }

  /*implicit val decoder: Decoder[Person] = Decoder.instance{ h =>
    for {
      personId <- h.get[String]("person_id")
      fName <- h.get[String]("fname")
      lName <- h.get[String]("lname")
      eyeColor <- h.get[String]("eye_color")
      birthdate <- h.get[LocalDate]("birth_date")
      street <- h.get[String]("street")
      city <- h.get[String]("city")
      state <- h.get[String]("state")
      country <- h.get[String]("country")
      postalCode <- h.get[String]("postal_code")
    } yield Person(personId, fName, lName, eyeColor, birthdate, street, city, state, country, postalCode)
  }*/

  def main(args: Array[String]): Unit = {
    import Domain._
    import Topics._

    val builder = new StreamsBuilder()

    val personStream: KStream[PersonId, String] = builder.stream[PersonId, String](PersonTopic)

    /*personStream.foreach { (id, d) =>
      println("STREAMED-----------------" + d)
    }*/
    personStream.to(Person3Topic)

    /* val personData = personStream.map { (_, data) =>
       val personJsonData = ((data \\ "payload").head \\ "after").head
       val personPlainId = (((data \\ "payload").head \\ "after").head \\ "person_id").head
       val personJsonId = personPlainId.noSpaces.toLong
       (personJsonId, personJsonData)
     }*/


    /*val newPerson: KStream[PersonId, Person] = personStream.mapValues { d =>
      d match {
        case Left(ex) => ""
        case Right(data) => data
      }
    }*/




    /*val countryStreamFilter = personStream.filter{ (id, data) =>
      val country = (((data \\ "payload").head \\ "after").head \\ "birth_date").head.asString
      country.contains("Iran")
    }*/





    val topology = builder.build()

    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app-02")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.70.24:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)
//    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)

    val application = new KafkaStreams(topology, props)
    application.start()
  }

}
