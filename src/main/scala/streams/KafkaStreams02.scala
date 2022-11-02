package streams

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax.{EncoderOps, _}
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

import java.util.Properties

object KafkaStreams02 {

  /*implicit def serde[A >: Null : Decoder : Encoder]: Serde[A] = {
    val serializer = (a: A) => a.asJson.noSpaces.getBytes()
    val deserializer = (bytes: Array[Byte]) => {
      val string = new String(bytes)
      decode[A](string).toOption
    }
    Serdes.fromFn[A](serializer, deserializer)
  }*/

  def main(args: Array[String]): Unit = {
    val builder = new StreamsBuilder()

    val personStreams: KStream[String, String] = builder.stream[String, String]("db_modiseh.kafka_test.person")
//    val personSelectFirstName: KStream[String, String] = personStreams.mapValues((_, v) => v.toUpperCase)

//    personSelectFirstName.to("db_modiseh.kafka_test.person4")
    personStreams.to("db_modiseh.kafka_test.person2")

    println("I HAVE STREAMED MESSAGE: " + personStreams)

    val topology = builder.build()

    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "person-test-application")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.70.24:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.stringSerde.getClass)

    val application = new KafkaStreams(topology, props)

    application.start()

  }

}
