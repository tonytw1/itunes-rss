package com.gu.itunes

object SecretKeeper {

  def getApiKey(): Option[String] = {
    Option(System.getenv("API_KEY"))
  }

}
