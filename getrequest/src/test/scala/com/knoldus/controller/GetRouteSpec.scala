package com.knoldus.controller

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.json.JsonSupport
import org.scalatest.{Matchers, WordSpecLike}

class GetRouteSpec extends WordSpecLike  with JsonSupport
  with ScalatestRouteTest with Matchers with GetRoute {

  "Get routes" should {
    "return message in case of validRequest " in {
      Get("/hello/shiv/hello") ~> getRoute ~> check {
        responseAs[String] shouldEqual( {"""{"message":"hello","name":"shiv"}"""})
      }
    }
  }

}
