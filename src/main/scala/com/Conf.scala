package com

import com.typesafe.config.ConfigFactory

object Conf {
  lazy val configuration = ConfigFactory.load()

  object Http {
    lazy val host = configuration.getString("http.host")
    lazy val port = configuration.getInt("http.port")
  }

  object Vstat {
    lazy val useRealService = configuration.getBoolean("vstat.useRealService")
    lazy val mockedResponse = configuration.getInt("vstat.mockedResponse")
    lazy val cookie = configuration.getString("vstat.cookie")
  }
}
