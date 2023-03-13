package com

import cats.effect.unsafe.implicits.global
import cats.effect.{ExitCode, IO, IOApp}
import com.services.{DomainService, MockedVstatService, RealVstatService, TrustpilotService}
import org.http4s.server.blaze.BlazeServerBuilder
import sttp.client3._
import sttp.client3.armeria.cats.ArmeriaCatsBackend

import scala.concurrent.ExecutionContext


object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val backend: SttpBackend[IO, Any] = ArmeriaCatsBackend[IO]()
    val vstatService = if (Conf.Vstat.useRealService) new RealVstatService(backend) else new MockedVstatService
    val trustpilotService = new TrustpilotService(backend)
    val domainService = new DomainService(vstatService, trustpilotService)
    val httpServer = new HttpServer(domainService)

    BlazeServerBuilder[IO](ExecutionContext.global)
      .bindHttp(Conf.Http.port, Conf.Http.host)
      .withHttpApp(httpServer.routes.orNotFound)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}


