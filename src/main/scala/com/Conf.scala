package com

import com.typesafe.config.ConfigFactory

object Conf {
  val configuration = ConfigFactory.load()

  object Http {
    val host = configuration.getString("http.host")
    val port = configuration.getInt("http.port")
  }

  object Vstat {
    val useRealService = configuration.getBoolean("vstat.useRealService")
    val mockedResponse = configuration.getInt("vstat.mockedResponse")
    val cookie = configuration.getString("vstat.cookie")
  }
}
