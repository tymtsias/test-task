package com
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.Models.Category.Jewelery
import com.linecorp.armeria.common.Request
import sttp.client3.RequestT
import sttp.client3.armeria.cats.ArmeriaCatsBackend
import sttp.client3._
import sttp.model.Uri
import sttp.model.Uri.Authority
import sttp.model.Uri.QuerySegment.KeyValue
import io.circe.syntax._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.{global => scalaGlobal}
import scala.concurrent.duration.Duration
import io.circe.parser._

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try

object Main extends App {
  val backend: SttpBackend[IO, Any] = ArmeriaCatsBackend[IO]()
  val trustpilotService = new TrustpilotService(backend)
  val future = trustpilotService.getByCategory(Jewelery).unsafeToFuture()

  future.foreach(println)


  Await.result(future, Duration.Inf)
  ArmeriaCatsBackend.usingDefaultClient[IO]()

}


