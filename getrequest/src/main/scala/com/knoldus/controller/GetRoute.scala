package com.knoldus.controller
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import com.knoldus.json.JsonSupport
import com.knoldus.service.GreetingService
import com.knoldus.util.Logging

import scala.language.postfixOps

trait GetRoute extends JsonSupport with Logging {

  lazy val getRoute: Route =
    get {
      path("Hello" / Segment / Segment) { (name: String, message: String) =>
        complete(GreetingService.getDetails(name, message))
      }

    }
}