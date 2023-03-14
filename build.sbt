ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.6"
lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(UniversalPlugin)
  .enablePlugins(DockerPlugin)
  .enablePlugins(DockerSpotifyClientPlugin)
  .settings(
    name := "test-task",
    mainClass := Some("com.Main")
  )
javacOptions ++= Seq("-source", "11")
dockerBaseImage := "openjdk:11-jdk"
dockerExposedPorts := Seq(8080)

libraryDependencies += "javax.activation" % "activation" % "1.1.1"
libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.8.13"
libraryDependencies += "org.slf4j" % "slf4j-jdk14" % "1.7.36"
libraryDependencies += "com.softwaremill.sttp.client3" %% "armeria-backend-cats" % "3.8.13"
libraryDependencies += "com.github.cb372" %% "scalacache-circe" % "0.28.0"
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect-kernel" % "3.4.8",
  "org.typelevel" %% "cats-effect-laws" % "3.4.8" % Test
)
libraryDependencies += "com.typesafe" % "config" % "1.4.2"
libraryDependencies += "io.circe" %% "circe-parser" % "0.15.0-M1"
libraryDependencies += "com.github.cb372" %% "scalacache-caffeine" % "0.28.0"
libraryDependencies += "com.github.cb372" %% "scalacache-core" % "0.28.0"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-generic" % "0.15.0-M1",
  "io.circe" %% "circe-literal" % "0.15.0-M1"
)
val http4sVersion = "1.0.0-M29"
resolvers += Resolver.sonatypeRepo("snapshots")
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-server" % http4sVersion,
  "org.http4s" %% "http4s-core" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion
)
dependencyOverrides += "org.typelevel" %% "cats-effect" % "3.5.0-RC3"


