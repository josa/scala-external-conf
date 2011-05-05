package br.com.gfuture.scalaexternalconf

import java.io.{ File, FileInputStream, IOException }
import org.slf4j.LoggerFactory

object Configuration extends Configuration

trait Configuration {

  private lazy val logger = LoggerFactory.getLogger(getClass)

  private lazy val properties: java.util.Properties = loadProperties

  def get(key: String): String = properties.getProperty(key)

  protected def loadProperties: java.util.Properties = {
    val props = new java.util.Properties
    System.getProperty("external.conf.path") match {
      case configFile: String =>
        val pathFile = new File(configFile)

        if (!pathFile.isDirectory)
          throw new RuntimeException("Error: o path %s is not a directory".format(pathFile.getAbsolutePath));

        for (file <- pathFile.listFiles) {
          if (!file.isDirectory) {
            var stream = new FileInputStream(file)
            loadStreamAndDispose(props load stream, stream.close)
          }
        }

        if (logger.isDebugEnabled)
          logger.debug("carregado configuracoes de: %s" format (configFile))
      case _ =>
        throw new RuntimeException("erro ao carregar as configurações do sistema, configure -Dexternal.conf.path=path/to/conf")
    }
    props
  }

  protected def loadStreamAndDispose(action: => Unit, disposal: => Unit) = {
    try {
      action
    } finally {
      try {
        disposal
      } catch {
        case e: IOException =>
          logger.error("erro loading properties", e)
      }
    }
  }

}