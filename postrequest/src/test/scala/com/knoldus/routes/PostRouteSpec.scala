package com.knoldus.routes

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.MessageEntity
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.json.JsonSupport
import com.knoldus.model.PostResponse
import org.scalatest.concurrent.ScalaFutures._
import org.scalatest.{Matchers, WordSpecLike}

class PostRouteSpec extends PostRoute with WordSpecLike with JsonSupport
  with ScalatestRouteTest with Matchers {

  "Post routes" should {

    val entity = Marshal(PostResponse(List(1, 2), "+")).to[MessageEntity].futureValue

    val invalidEntity =
      Marshal(PostResponse(List(1, 2), "+-")).to[MessageEntity].futureValue
    "return message in case of validRequest " in {
      Post("/calculator").withEntity(entity) ~> postRoute ~> check {
        responseAs[String] shouldEqual "3"
      }
    }

    "return default message in case of invalidRequest " in {
      Post("/calculator").withEntity(invalidEntity) ~> postRoute ~> check {
        responseAs[String] shouldEqual "Does not support"
      }
    }
  }
}
