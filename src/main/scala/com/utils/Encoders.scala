package com.utils

import com.models.{DomainData, Domains}
import io.circe.Encoder
import io.circe.generic.semiauto._

object Encoders {
  implicit lazy val domainDataEncoder: Encoder[DomainData] = deriveEncoder
  implicit lazy val domainsEncoder: Encoder[Domains] = deriveEncoder
}
