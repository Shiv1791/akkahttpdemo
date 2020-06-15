package com.knoldus.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.model.PostResponse
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val postFormat: RootJsonFormat[PostResponse] = jsonFormat2(PostResponse)

}
