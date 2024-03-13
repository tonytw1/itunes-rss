package com.gu.itunes

import org.slf4j.LoggerFactory

object SecretKeeper {

  private val logger = LoggerFactory.getLogger(getClass)

  def getApiKey(): Option[String] = {
    val maybeString = Option(System.getenv("API_KEY"))
    logger.info("Got API KEY from ENV: " + maybeString)
    maybeString
  }

}
