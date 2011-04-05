package main.scala.br.com.guture.scalaexternalconf

import java.io.{InputStream, File, FileInputStream}
import org.slf4j.LoggerFactory

object Configuration extends Configuration

trait Configuration {

  private lazy val logger = LoggerFactory.getLogger(getClass)

  lazy val properties: java.util.Properties = loadProperties

  protected def loadProperties: java.util.Properties = {
    val props = new java.util.Properties
    var stream: InputStream = null
    System.getProperty("external.conf.path") match {
      case configFile: String =>
        stream = new FileInputStream(new File(configFile))
        if (logger.isDebugEnabled)
          logger.debug("carregado configuracoes de: %s" format (configFile))
      case _ =>
        logger.error("erro ao carregar as configurações do sistema")
    }
    if (stream ne null)
      quietlyDispose(props load stream, stream.close)
    props
  }


}