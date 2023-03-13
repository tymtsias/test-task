package com.services

import cats.effect.IO
import com.Conf
import com.models.Domain

class MockedVstatService extends VstatService {

  def totalCount(domain: Domain): IO[Int] = {
    IO(Conf.Vstat.mockedResponse)
  }

}
