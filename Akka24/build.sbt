name := """Akka_vs_Node"""

organization  := "ch.weisenburger"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Typesafe releases" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0-M5",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.3"
)
