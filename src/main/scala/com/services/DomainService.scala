package com.services

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import com.github.benmanes.caffeine.cache.Caffeine
import com.models.{Category, DomainData, Domains}
import scalacache.caffeine.CaffeineCache
import scalacache.{Cache, Entry, Mode}
import cats.implicits._

import scala.concurrent.duration.DurationInt
import scalacache.modes.scalaFuture._

import scala.concurrent.ExecutionContext.Implicits.global


class DomainService(vstatService: VstatService, trustpilotService: TrustpilotService)(implicit x: IORuntime) {
  val underlyingCaffeineCache = Caffeine.newBuilder().maximumSize(10000L).build[String, Entry[Domains]]
  implicit val customisedCaffeineCache = CaffeineCache(underlyingCaffeineCache)

  private def updateCache(category: Category): IO[Domains] = {
    trustpilotService.getByCategory(category).flatMap{ maybeValue =>
      maybeValue match {
        case Some(value) => value.sortBy(domain => domain.lastComment).reverse.map{domain =>
          vstatService.totalCount(domain).map{totalCount =>
            DomainData(domain.name, domain.numberOfReviews, totalCount)
          }}.sequence.map(domains => Domains(domains.toList))
        case None => IO(Domains(Nil))
      }
    }
  }

  def get(category: Category): IO[Domains] =
    IO.fromFuture(IO(customisedCaffeineCache.cachingF(category.name)(ttl = Some(2.minutes))(f = updateCache(category).unsafeToFuture())))

}
