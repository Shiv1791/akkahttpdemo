package com.knoldus.service

import com.knoldus.model.Response

object GreetingService {
  def getDetails(name: String, message: String): Response = {
    Response(name, message)
  }
}