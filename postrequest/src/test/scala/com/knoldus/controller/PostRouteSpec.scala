package com.knoldus.controller

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.knoldus.json.JsonSupport
import org.mockito.MockitoSugar
import org.scalatest.wordspec.AnyWordSpecLike

import scala.concurrent.duration._

class PostRouteSpec extends PostRoute with AnyWordSpecLike with JsonSupport
  with ScalatestRouteTest with MockitoSugar {


  override implicit val materializer: ActorMaterializer = ActorMaterializer()
  override implicit lazy val timeout: Timeout = Timeout(100.seconds)
  "Answer routes" should {

    implicit val defaultTimeout: RouteTestTimeout = RouteTestTimeout(5.seconds)

    "return Error message in case of invalidRequest " in {
      Get("/postResponse") ~> postRoute ~> check {
        assert(status === StatusCodes.Accepted)
      }
    }
  }
}
