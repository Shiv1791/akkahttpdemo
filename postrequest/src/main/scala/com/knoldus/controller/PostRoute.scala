package com.knoldus.controller

import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.knoldus.json.JsonSupport
import com.knoldus.model.PostResponse

import scala.concurrent.duration._
import com.knoldus.service.CalculatorService
import com.knoldus.util.Logging

import scala.language.postfixOps

trait PostRoute extends JsonSupport with Logging {
  lazy val postRoute: Route =
    post {
      entity(as[PostResponse]){
       postResponse => {
          complete(CalculatorService.getDetails(postResponse))
        }
      }
    }

  implicit lazy val timeout: Timeout = Timeout(100.seconds)
}