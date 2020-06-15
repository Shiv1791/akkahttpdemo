package com.knoldus.util
import org.slf4j.{Logger, LoggerFactory}

import scala.util.Try

trait Logging {
  self =>

  import LogLevel._

  private val logLevels = List("debug", "info", "warn", "error")

  private val logLevel = Try(CommonConfig.getValue("app.log.level")).toOption
    .getOrElse("info").toLowerCase.trim

  private val logLevelId = logLevels.indexOf(logLevel)

  protected val logger: Logger = LoggerFactory.getLogger(self.getClass)

  protected def debug(message: String): Unit = log(DEBUG, logger.debug, message)

  protected def debug(message: String, exception: Throwable): Unit = logger.debug(message, exception)

  protected def info(message: String): Unit = log(INFO, logger.info, message)

  protected def info(message: String, exception: Throwable): Unit = logExc(INFO, logger.info, message, exception)

  protected def warn(message: String): Unit = log(WARN, logger.warn, message)

  protected def warn(message: String, exception: Throwable): Unit = logExc(WARN, logger.warn, message, exception)

  protected def error(message: String): Unit = log(ERROR, logger.error, message)

  protected def error(message: String, exception: Throwable): Unit = logExc(ERROR, logger.error, message, exception)

  private def log(level: Value, logFunc: String => Unit, message: String): Unit = if (level.id >= logLevelId) logFunc(message)

  private def logExc(level: Value, logFunc: (String, Throwable) => Unit, message: String, exp: Throwable): Unit =
    if (level.id >= logLevelId) logFunc(message, exp)

}

object LogLevel extends Enumeration {
  type LogLevel = Value
  val DEBUG, INFO, WARN, ERROR = Value
}
