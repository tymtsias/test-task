package com.services

import cats.effect.IO
import com.models.Domain
import sttp.client3.{SttpBackend, basicRequest}
import sttp.model.Uri

class RealVstatService(backend: SttpBackend[IO, Any]) extends VstatService {

  def totalCount(domain: Domain): IO[Int] = {
    val ioResponse = backend.send(basicRequest.get(Uri.apply(
      scheme = "https",
      host = "web.vstat.info",
      port = Some(443),
      path = List(domain.name),
      querySegments = Nil,
      fragment = None,
      userInfo = None
    )).headers(Map("authority" -> "web.vstat.info", "cookie" -> "vstat_session=ErJt7YU24evVK9RUNNqcn95FhC5yai0jHAHmnVwN;")))
    ioResponse.map {
      _.body.toOption.flatMap { response =>
        val totalCount = response.split("\n").find(_.contains("MONTHLY_VISITS")).map(line => line.split("data-datum=\"")(1).split('"')(0))
        totalCount.flatMap(_.toIntOption)
      }
    }.map(_.getOrElse(0))
  }
}
