package tests

import io.circe.{Json, ParsingFailure, parser}

import java.text.SimpleDateFormat
import java.util.TimeZone

object Playground extends App {

  trait Stream[T]
  case class Product(name: String)
  case class User(userId: Int, name: String)

  val products = List(
    Product("iPhone"),
    Product("Macbook"),
    Product("iMac")
  )

  val mohsen = User(100, "Mohsen")

  val streams = Map(mohsen -> products)

  val mappedStreams = streams.map(_._2)
  val flatMappedStreams = streams.flatMap(_._2)

  /*println(mappedStreams)
  println(flatMappedStreams)*/

  /*mappedStreams.foreach(println)
  flatMappedStreams.foreach(println)*/

  /*println(Map(1 -> "one", 2 -> "two").flatMap(a => a._2.toUpperCase))
  println(Map(1 -> List(1, "one"), 2 -> List(2, "two")).map(_._2))
  println(Map(1 -> List(1, "one"), 2 -> List(2, "two")).flatMap(_._2))*/

  /*val jsonString = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"person_id\"},{\"type\":\"string\",\"optional\":true,\"field\":\"first_name\"},{\"type\":\"string\",\"optional\":true,\"field\":\"last_name\"},{\"type\":\"string\",\"optional\":true,\"name\":\"io.debezium.time.ZonedTimestamp\",\"version\":1,\"field\":\"birthdate\"},{\"type\":\"string\",\"optional\":true,\"field\":\"country\"},{\"type\":\"string\",\"optional\":true,\"field\":\"city\"}],\"optional\":true,\"name\":\"db_modiseh.kafka_test.person.Value\",\"field\":\"before\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"person_id\"},{\"type\":\"string\",\"optional\":true,\"field\":\"first_name\"},{\"type\":\"string\",\"optional\":true,\"field\":\"last_name\"},{\"type\":\"string\",\"optional\":true,\"name\":\"io.debezium.time.ZonedTimestamp\",\"version\":1,\"field\":\"birthdate\"},{\"type\":\"string\",\"optional\":true,\"field\":\"country\"},{\"type\":\"string\",\"optional\":true,\"field\":\"city\"}],\"optional\":true,\"name\":\"db_modiseh.kafka_test.person.Value\",\"field\":\"after\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"version\"},{\"type\":\"string\",\"optional\":false,\"field\":\"connector\"},{\"type\":\"string\",\"optional\":false,\"field\":\"name\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"ts_ms\"},{\"type\":\"string\",\"optional\":true,\"name\":\"io.debezium.data.Enum\",\"version\":1,\"parameters\":{\"allowed\":\"true,last,false,incremental\"},\"default\":\"false\",\"field\":\"snapshot\"},{\"type\":\"string\",\"optional\":false,\"field\":\"db\"},{\"type\":\"string\",\"optional\":true,\"field\":\"sequence\"},{\"type\":\"string\",\"optional\":true,\"field\":\"table\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"server_id\"},{\"type\":\"string\",\"optional\":true,\"field\":\"gtid\"},{\"type\":\"string\",\"optional\":false,\"field\":\"file\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"pos\"},{\"type\":\"int32\",\"optional\":false,\"field\":\"row\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"thread\"},{\"type\":\"string\",\"optional\":true,\"field\":\"query\"}],\"optional\":false,\"name\":\"io.debezium.connector.mysql.Source\",\"field\":\"source\"},{\"type\":\"string\",\"optional\":false,\"field\":\"op\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"ts_ms\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"id\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"total_order\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"data_collection_order\"}],\"optional\":true,\"field\":\"transaction\"}],\"optional\":false,\"name\":\"db_modiseh.kafka_test.person.Envelope\"},\"payload\":{\"before\":null,\"after\":{\"person_id\":354,\"first_name\":\"محسن\",\"last_name\":\"صادق\",\"birthdate\":\"2022-10-24T09:40:26Z\",\"country\":\"ایران\",\"city\":\"تهران\"},\"source\":{\"version\":\"1.9.6.Final\",\"connector\":\"mysql\",\"name\":\"db_modiseh\",\"ts_ms\":1666604426000,\"snapshot\":\"false\",\"db\":\"kafka_test\",\"sequence\":null,\"table\":\"person\",\"server_id\":223344,\"gtid\":\"37f2560b-fb6c-11ec-a785-005056affb44:669\",\"file\":\"mysql-bin.000090\",\"pos\":6311,\"row\":0,\"thread\":269,\"query\":null},\"op\":\"c\",\"ts_ms\":1666604426943,\"transaction\":null}}"
  val parsedJson: Either[ParsingFailure, Json] = parser.parse(jsonString)
  val valueRes = parsedJson match {
    case Left(parsingError) => throw new IllegalArgumentException(s"Invalid JSON object: ${parsingError.message}")
    case Right(json) => json
  }

  val payload = valueRes \\ "payload"
  val firstName = ((payload.head \\ "after").head \\ "first_name").head.toString()
  println(firstName)*/

  /*val country = "Iran"
  val countries = Map("Iran" -> "Asia", "Germany" -> "Europe", "USA" -> "America")
  val getPacific = country match {
    case "Iran" => countries.filter(pairs => pairs._1 == "Iran")
    case _ => "Nothing"
  }
  println(getPacific)
*/
  val utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
  utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"))

  val date = utcFormat.parse("2022-11-02T09:42:02Z")
  val irFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  irFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"))
  val check = irFormat.format(date)
  println(irFormat.format(date))

}
