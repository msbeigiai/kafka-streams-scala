package streams.domain

import io.circe.Decoder

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDate, LocalDateTime, ZoneId}
import java.util.{Locale, TimeZone}

object ClassesDomain {
  case class Person(personId: String, fName: String, lName: String, eyeColor: String, street: String,
                    city: String, state: String, country: String, postalCode: String, birthdate: LocalDateTime)

  case class PersonId(id: String)


  /*object Person {
    implicit val decoder: Decoder[Person] = Decoder.instance { h =>
      for {
        personId <- h.get[Long]("person_id")
        fName <- h.get[String]("fname")
        lName <- h.get[String]("lname")
        eyeColor <- h.get[String]("eye_color")
        street <- h.get[String]("street")
        city <- h.get[String]("city")
        state <- h.get[String]("state")
        country <- h.get[String]("country")
        postalCode <- h.get[String]("postal_code")
        birthdate <- h.get[Long]("birth_date")
      } yield Person(personId, fName, lName, eyeColor, street, city, state, country, postalCode,
        Instant.ofEpochMilli(birthdate).atZone(ZoneId.systemDefault()).toLocalDate
      )
    }
  }*/

  object Person {


    def dateParse(utcDateTime: String): LocalDateTime = {
      val utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
      utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
      val date = utcFormat.parse(utcDateTime)
      val irFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      irFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"))
      val dateFormatted = irFormat.format(date)
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
      LocalDateTime.parse(dateFormatted, formatter)
    }


    implicit val decoder: Decoder[Person] = Decoder.instance { h =>
      for {
        personId <- h.get[Int]("person_id")
        fName <- h.get[String]("fname")
        lName <- h.get[String]("lname")
        eyeColor <- h.get[String]("eye_color")
        street <- h.get[String]("street")
        city <- h.get[String]("city")
        state <- h.get[String]("state")
        country <- h.get[String]("country")
        postalCode <- h.get[String]("postal_code")
        birthdate <- h.get[String]("birth_date")
      } yield Person(personId.toString, fName, lName, eyeColor, street, city, state, country, postalCode,
        dateParse(birthdate))

    }
  }

  object PersonId {
    implicit val decoder: Decoder[PersonId] = Decoder.instance { h =>
      for {
        personId <- h.get[Long]("person_id")
      } yield PersonId(personId.toString)
    }
  }

  case class FavoriteFood(personId: String, food: String)
  object FavoriteFood {
    implicit val decoder: Decoder[FavoriteFood] = Decoder.instance { h =>
      for {
        personId <- h.get[Int]("person_id")
        food <- h.get[String]("food")
      } yield FavoriteFood(personId.toString, food)
    }
  }

}