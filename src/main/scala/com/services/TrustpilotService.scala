package com.services

import cats.effect.IO
import com.models.{Category, Domain}
import io.circe.parser._
import sttp.client3._
import sttp.model.Uri
import sttp.model.Uri.QuerySegment.KeyValue

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try


class TrustpilotService(backend: SttpBackend[IO, Any]) {

  private val start = "<script id=\"__NEXT_DATA__\" type=\"application/json\""
  private val startNonce = "=\">"
  private val end = "</script>"
  private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")


  def getByCategory(category: Category): IO[Option[Seq[Domain]]] = {
    val ioResponse = backend.send(basicRequest.get(Uri.apply(
      scheme = "https",
      host = "www.trustpilot.com",
      port = Some(443),
      path = List("categories", category.name),
      querySegments = List(KeyValue("sort", "latest_review")),
      fragment = None,
      userInfo = None
    )))

    ioResponse.map { response =>
      response.body match {
        case Left(_) => None
        case Right(value) =>
          val fullResult = value.split(start)(1).split(startNonce)(1).split(end)(0)
          parse(fullResult).toOption.flatMap { json =>
            json.hcursor.downField("props").downField("pageProps").downField("recentlyReviewedBusinessUnits").values.map { x =>
              x.map { json =>
                val name = json.hcursor.downField("identifyingName").focus
                val numberOfReviews = json.hcursor.downField("numberOfReviews").focus
                val lastReviewDate = json.hcursor.downField("review").downField("date").downField("createdAt").focus.flatMap { dateJson =>
                  dateJson.asString.flatMap { dateString =>
                    Try(LocalDateTime.parse(dateString.dropRight(5), dateFormat)).toOption
                  }
                }
                (name.flatMap(_.asString), numberOfReviews.flatMap(_.asNumber.flatMap(_.toInt)), lastReviewDate)
              }.toList.collect{
                case (Some(x), Some(y), Some(z)) => Domain(x, y, z)
              }
            }
          }
      }
    }
  }



}
