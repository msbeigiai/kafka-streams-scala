package streams

import io.circe._
import io.circe.parser._
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

import java.util.Properties

object KafkaStreams03 {

  object Domain {
    type PersonId = String
    type PersonData = String
  }

  object Topics {
    val PersonTopic = "db_modiseh.kafka_test.person"
  }

 /* implicit def serde[A >: Null : Decoder : Encoder]: Serde[A] = {
    val serializer = (a: A) => a.asJson.noSpaces.getBytes()
    val deserializer = (bytes: Array[Byte]) => {
      val string = new String(bytes)
      decode[A](string).toOption
    }
    Serdes.fromFn[A](serializer, deserializer)
  }*/

  def main(args: Array[String]): Unit = {
    import Domain._
    import Topics._

    val builder = new StreamsBuilder()

    val personStream: KStream[PersonId, PersonData] = builder.stream[PersonId, PersonData](PersonTopic)

    /*personStream.foreach { (id, data) =>
      println("STREAMED +++++ " + data)
    }*/

    /*val firstNameStream: KStream[PersonId, String] = personStream.filter { (id, j) =>
      val payload = (j \\ "payload").head
      val firstName = ((payload \\ "after").head \\ "first_name").head.toString
      firstName == "Mohsen"
    }*/

//    firstNameStream.to("test-person2")

    /*firstNameStream.foreach { (id, j) =>
      println("STREAMED +++++++ : " + j)
    }*/

    /*personStream.foreach { (id, j) =>
      val payload = (j \\ "payload").head
      val firstName = ((payload \\ "after").head \\ "first_name").head.toString
      println("THE FIRST NAME IS : +++++++ : " + firstName)
    }*/

    def checkForName: (PersonId, Json) => Boolean = (name, data) => {
      val payload = (data \\ "payload").head
      val firstName = ((payload \\ "after").head \\ "first_name").head.toString
      firstName == name
    }

    def successParse: String => Json = data => {
      val parseData = parse(data)
      parseData match {
        case Left(failure) => throw new IllegalArgumentException(s"not converted to json....${failure.message}")
        case Right(json) => json
      }
    }

    val firstNameStream: KStream[PersonId, PersonData] = personStream.filter { (id: PersonId, data: PersonData) =>
      val parsedToJson = successParse(data)
      checkForName("Mohsen", parsedToJson)
    }


    firstNameStream.foreach { (id, data) =>
      println("STREAMED ************************ " + parse(data))
    }


    val topology = builder.build()

    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app-01")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.70.24:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)

    val application = new KafkaStreams(topology, props)
    application.start()
  }

}
