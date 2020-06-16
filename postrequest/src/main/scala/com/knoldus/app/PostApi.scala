package com.knoldus.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.knoldus.controller.PostRoute
import com.knoldus.util.Constants.DEFAULT_PORT

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn
import scala.util.Try

object PostApi extends PostRoute with App  {


  implicit val system: ActorSystem = ActorSystem("Post-Actor-System")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val host = "localhost"
  val port = DEFAULT_PORT
  val route = postRoute
  val bindingFuture = Http().bindAndHandle(route, host, port)


  println(s"Server online at http://" + s"$host" + s":$port" + "\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
