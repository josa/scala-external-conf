package main.scala.br.com.guture.scalaexternalconf

import java.io.{File, FileInputStream, IOException}
import org.slf4j.LoggerFactory

object Configuration extends Configuration

trait Configuration {

  private lazy val logger = LoggerFactory.getLogger(getClass)

  lazy val properties: java.util.Properties = loadProperties

  protected def loadProperties: java.util.Properties = {
    val props = new java.util.Properties
    System.getProperty("external.conf.path") match {
      case configFile: String =>

        val pathFile = new File(configFile)

        if (!pathFile.isDirectory)
          throw new RuntimeException("Error: o path %s is not a directory".format(pathFile.getAbsolutePath));

        for (file <- pathFile.listFiles) {
          var stream = new FileInputStream(file)
          loadStreamAndDispose(props load stream, stream.close)
        }

        if (logger.isDebugEnabled)
          logger.debug("carregado configuracoes de: %s" format (configFile))

      case _ =>
        logger.error("erro ao carregar as configurações do sistema")
    }
    props
  }

  protected def loadStreamAndDispose(action: => Unit, disposal: => Unit) = {
    try {
      action
    }
    finally {
      try {
        disposal
      }
      catch {
        case e: IOException =>
          logger.error("erro loading properties", e)
      }
    }
  }

}