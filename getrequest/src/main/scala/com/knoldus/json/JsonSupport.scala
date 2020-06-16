package com.knoldus.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.model.Response
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val responseFormat: RootJsonFormat[Response] = jsonFormat2(Response)
}
