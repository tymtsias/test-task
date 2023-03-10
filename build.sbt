ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "test-task"
  )

libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.8.13"
libraryDependencies += "com.softwaremill.sttp.client3" %% "armeria-backend-cats" % "3.8.13"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.0-RC3"
libraryDependencies += "io.circe" %% "circe-parser" % "0.15.0-M1"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-generic" % "0.15.0-M1",
  "io.circe" %% "circe-literal" % "0.15.0-M1"
)
