package com.services

import cats.effect.IO
import com.models.Domain

trait VstatService {
  def totalCount(domain: Domain): IO[Int]
}
