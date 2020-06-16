package com.knoldus.controller
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import com.knoldus.json.JsonSupport
import com.knoldus.service.GreetingService


import scala.language.postfixOps

trait GetRoute extends JsonSupport  {

  lazy val getRoute: Route =
    get {
      path("hello" / Segment / Segment) { (name: String, message: String) =>
        complete(GreetingService.getDetails(name, message))
      }

    }
}