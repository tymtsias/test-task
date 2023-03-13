package com

import cats.effect.IO
import com.models.Category
import com.services.DomainService
import io.circe.syntax._
import org.http4s._
import org.http4s.dsl.io._
import com.utils.Encoders._

class HttpServer(domainService: DomainService) {

  val routes = HttpRoutes.of[IO] {
    case GET -> Root / "category" / category => {
      Category.find(category) match {
        case Some(value) => domainService.get(value).flatMap{domains =>
          Ok(domains.asJson.noSpaces)
        }
        case None => IO(Response(NotFound))
      }
    }
  }


}
