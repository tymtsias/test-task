package com

import cats.effect.IO
import com.Models.Domain
import sttp.client3.{RequestT, SttpBackend, basicRequest}
import sttp.model.Uri

class VstatService(backend: SttpBackend[IO, Any]) {

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
  }
}
