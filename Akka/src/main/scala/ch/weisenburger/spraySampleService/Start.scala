package ch.weisenburger.spraySampleService

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

object Start extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("spray-can")
  val handlerSystem = ActorSystem("handler-system", ConfigFactory.load("handler-system"))


  // create and start our service actor
  val service = handlerSystem.actorOf(FromConfig.props(Props[MyServiceActor]), "router1")

  //val service = system.actorOf(Props[MyServiceActor], "demo-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "127.0.0.1", port = 9090)
}