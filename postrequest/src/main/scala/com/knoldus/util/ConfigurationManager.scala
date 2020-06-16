package com.knoldus.util

import com.typesafe.config.{ Config, ConfigFactory }

import scala.collection.JavaConverters._
import scala.util.Try

/**
 * Handles application's configuration file.
 */
class ConfigurationManager(configPath: Option[String] = None) {

  private val root_config = ConfigFactory.load()

  protected val config: Config = configPath match {
    case Some(path) => root_config.getConfig(path)
    case None => root_config
  }

  private def tryToOpt[T](f: => T): Option[T] = Try(f).toOption

  def getIntOpt(key: String): Option[Int] = tryToOpt(config.getInt(key))

  def getValue(key: String): String = config.getString(key)
  def getValueOpt(key: String): Option[String] = tryToOpt(config.getString(key))

}

object RoutesConfig extends ConfigurationManager(Some("routes"))

object CommonConfig extends ConfigurationManager
