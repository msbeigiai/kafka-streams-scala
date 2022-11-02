package streams.jsonstrings

object JsonStrings extends App {

  object JsonData {
    val rawJsonString: String =
      """
        |{
        |  "schema": {
        |    "type": "struct",
        |    "fields": [
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "int32",
        |            "optional": false,
        |            "field": "person_id"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "fname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "lname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.data.Enum",
        |            "version": 1,
        |            "parameters": {
        |              "allowed": "BR,BL,GR"
        |            },
        |            "field": "eye_color"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "street"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "city"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "state"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "country"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "postal_code"
        |          },
        |          {
        |            "type": "int32",
        |            "optional": true,
        |            "name": "org.apache.kafka.connect.data.Date",
        |            "version": 1,
        |            "field": "birth_date"
        |          }
        |        ],
        |        "optional": true,
        |        "name": "db_modiseh.kafka_test.person.Value",
        |        "field": "before"
        |      },
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "int32",
        |            "optional": false,
        |            "field": "person_id"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "fname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "lname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.data.Enum",
        |            "version": 1,
        |            "parameters": {
        |              "allowed": "BR,BL,GR"
        |            },
        |            "field": "eye_color"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "street"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "city"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "state"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "country"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "postal_code"
        |          },
        |          {
        |            "type": "int32",
        |            "optional": true,
        |            "name": "org.apache.kafka.connect.data.Date",
        |            "version": 1,
        |            "field": "birth_date"
        |          }
        |        ],
        |        "optional": true,
        |        "name": "db_modiseh.kafka_test.person.Value",
        |        "field": "after"
        |      },
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "version"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "connector"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "name"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "ts_ms"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.data.Enum",
        |            "version": 1,
        |            "parameters": {
        |              "allowed": "true,last,false,incremental"
        |            },
        |            "default": "false",
        |            "field": "snapshot"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "db"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "sequence"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "table"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "server_id"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "gtid"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "file"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "pos"
        |          },
        |          {
        |            "type": "int32",
        |            "optional": false,
        |            "field": "row"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": true,
        |            "field": "thread"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "query"
        |          }
        |        ],
        |        "optional": false,
        |        "name": "io.debezium.connector.mysql.Source",
        |        "field": "source"
        |      },
        |      {
        |        "type": "string",
        |        "optional": false,
        |        "field": "op"
        |      },
        |      {
        |        "type": "int64",
        |        "optional": true,
        |        "field": "ts_ms"
        |      },
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "id"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "total_order"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "data_collection_order"
        |          }
        |        ],
        |        "optional": true,
        |        "field": "transaction"
        |      }
        |    ],
        |    "optional": false,
        |    "name": "db_modiseh.kafka_test.person.Envelope"
        |  },
        |  "payload": {
        |    "before": null,
        |    "after": {
        |      "person_id": 3,
        |      "fname": "Lorenzo",
        |      "lname": "Bruen",
        |      "eye_color": "BL",
        |      "street": "846 Destinee Greens",
        |      "city": "Trinity",
        |      "state": "VA",
        |      "country": "Saint Kitts",
        |      "postal_code": "18011",
        |      "birth_date": 19195
        |    },
        |    "source": {
        |      "version": "1.9.6.Final",
        |      "connector": "mysql",
        |      "name": "db_modiseh",
        |      "ts_ms": 1667026823000,
        |      "snapshot": "false",
        |      "db": "kafka_test",
        |      "sequence": null,
        |      "table": "person",
        |      "server_id": 223344,
        |      "gtid": "37f2560b-fb6c-11ec-a785-005056affb44:821",
        |      "file": "mysql-bin.000095",
        |      "pos": 7057,
        |      "row": 0,
        |      "thread": 293,
        |      "query": null
        |    },
        |    "op": "c",
        |    "ts_ms": 1667026823322,
        |    "transaction": null
        |  }
        |}
        |""".stripMargin

    val rawJsonString2: String =
      """
        |{
        |    "person_id": 3,
        |    "fname": "Lorenzo",
        |    "lname": "Bruen",
        |    "eye_color": "BL",
        |    "street": "846 Destinee Greens",
        |    "city": "Trinity",
        |    "state": "VA",
        |    "country": "Saint Kitts",
        |    "postal_code": "18011",
        |    "birth_date": 19195
        |}
        |""".stripMargin

    val rawJsonTimestamp: String =
      """
        |{
        |  "schema": {
        |    "type": "struct",
        |    "fields": [
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "int32",
        |            "optional": false,
        |            "field": "person_id"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "fname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "lname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.data.Enum",
        |            "version": 1,
        |            "parameters": {
        |              "allowed": "BR,BL,GR"
        |            },
        |            "field": "eye_color"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "street"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "city"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "state"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "country"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "postal_code"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.time.ZonedTimestamp",
        |            "version": 1,
        |            "field": "birth_date"
        |          }
        |        ],
        |        "optional": true,
        |        "name": "db_modiseh.kafka_test.person.Value",
        |        "field": "before"
        |      },
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "int32",
        |            "optional": false,
        |            "field": "person_id"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "fname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "lname"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.data.Enum",
        |            "version": 1,
        |            "parameters": {
        |              "allowed": "BR,BL,GR"
        |            },
        |            "field": "eye_color"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "street"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "city"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "state"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "country"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "postal_code"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.time.ZonedTimestamp",
        |            "version": 1,
        |            "field": "birth_date"
        |          }
        |        ],
        |        "optional": true,
        |        "name": "db_modiseh.kafka_test.person.Value",
        |        "field": "after"
        |      },
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "version"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "connector"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "name"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "ts_ms"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "name": "io.debezium.data.Enum",
        |            "version": 1,
        |            "parameters": {
        |              "allowed": "true,last,false,incremental"
        |            },
        |            "default": "false",
        |            "field": "snapshot"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "db"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "sequence"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "table"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "server_id"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "gtid"
        |          },
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "file"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "pos"
        |          },
        |          {
        |            "type": "int32",
        |            "optional": false,
        |            "field": "row"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": true,
        |            "field": "thread"
        |          },
        |          {
        |            "type": "string",
        |            "optional": true,
        |            "field": "query"
        |          }
        |        ],
        |        "optional": false,
        |        "name": "io.debezium.connector.mysql.Source",
        |        "field": "source"
        |      },
        |      {
        |        "type": "string",
        |        "optional": false,
        |        "field": "op"
        |      },
        |      {
        |        "type": "int64",
        |        "optional": true,
        |        "field": "ts_ms"
        |      },
        |      {
        |        "type": "struct",
        |        "fields": [
        |          {
        |            "type": "string",
        |            "optional": false,
        |            "field": "id"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "total_order"
        |          },
        |          {
        |            "type": "int64",
        |            "optional": false,
        |            "field": "data_collection_order"
        |          }
        |        ],
        |        "optional": true,
        |        "field": "transaction"
        |      }
        |    ],
        |    "optional": false,
        |    "name": "db_modiseh.kafka_test.person.Envelope"
        |  },
        |  "payload": {
        |    "before": null,
        |    "after": {
        |      "person_id": 12,
        |      "fname": "Sara",
        |      "lname": "Akbari",
        |      "eye_color": "BL",
        |      "street": "5512 kolp plos",
        |      "city": "Tehran",
        |      "state": "PL",
        |      "country": "LS",
        |      "postal_code": "40021",
        |      "birth_date": "1983-04-01T20:30:00Z"
        |    },
        |    "source": {
        |      "version": "1.9.6.Final",
        |      "connector": "mysql",
        |      "name": "db_modiseh",
        |      "ts_ms": 1667295805000,
        |      "snapshot": "false",
        |      "db": "kafka_test",
        |      "sequence": null,
        |      "table": "person",
        |      "server_id": 223344,
        |      "gtid": "37f2560b-fb6c-11ec-a785-005056affb44:838",
        |      "file": "mysql-bin.000098",
        |      "pos": 3327,
        |      "row": 0,
        |      "thread": 355,
        |      "query": null
        |    },
        |    "op": "c",
        |    "ts_ms": 1667295806268,
        |    "transaction": null
        |  }
        |}
        |""".stripMargin
  }



}
