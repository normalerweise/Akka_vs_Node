package ch.weisenburger

import akka.actor._
import akka.http.Http
import akka.http.model.{MediaTypes, HttpEntity, HttpResponse}
import akka.http.server.Directives._
import akka.http.server.{RoutingLog, RoutingSettings, RoutingSetup, Route}
import akka.stream.ActorFlowMaterializer


object Server extends App {
  implicit val system = ActorSystem("akkavsnode")
  implicit val materializer = ActorFlowMaterializer()
  implicit val executor = system.dispatcher
  implicit val setup = RoutingSetup(RoutingSettings.default, null, null, executor, materializer, RoutingLog.fromActorSystem)

  val result =  <html>
    <body>
      <h1>Hello World! I'm HTTP on Akka :-)</h1>
    </body>
  </html>

  val routes = path("") {
      complete(HttpResponse(
        entity = HttpEntity(MediaTypes.`text/html`, result.toString)))
    }
  val handler = Route.handlerFlow(routes)
  Http(system).bindAndHandle(handler, interface = "0.0.0.0", 9092)


}
