package com.knoldus.app
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.knoldus.controller.GetRoute
import com.knoldus.util.{Logging, RoutesConfig, Constants}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn
import scala.util.Try

object GetApi extends GetRoute with App with Logging {

  //override val videoEngineService = VideoEngineServiceImpl
  implicit val system: ActorSystem = ActorSystem("Get-Actor-System")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val host = Try(RoutesConfig.getValue("host")).toOption.getOrElse("localhost")
  val port = RoutesConfig.getIntOpt("port").getOrElse(Constants.DEFAULT_PORT)
  val route = getRoute
  val bindingFuture = Http().bindAndHandle(route, host, port)


  info(s"Server online at http://" + s"$host" + s":$port" + "\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}