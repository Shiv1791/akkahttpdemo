package com.knoldus.controller

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, _}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.knoldus.json.JsonSupport
import com.knoldus.model.PostResponse
import com.knoldus.service.CalculatorService
import com.knoldus.util.Logging

import scala.concurrent.duration._
import scala.language.postfixOps

trait PostRoute extends JsonSupport with Logging {
  lazy val postRoute: Route =
    post {
      path("postResponse") {
      entity(as[PostResponse]) {
        postResponse => {
          complete(StatusCodes.Accepted, CalculatorService.getDetails(postResponse))
        }
      }
      }
    }

  implicit lazy val timeout: Timeout = Timeout(100.seconds)
}