package test.scala.br.com.gfuture.scalaexternalconf

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{Spec, BeforeAndAfterEach}
import main.scala.br.com.guture.scalaexternalconf.Configuration

class ConfigurationSpec extends Spec with ShouldMatchers with BeforeAndAfterEach {

  describe("Configuration"){

    it("should load one property from file"){
      Configuration.properties.get("br.com.property1") should equal("property1")
    }

    it("should load other property from file"){
      Configuration.properties.get("br.com.property2") should equal("property2")
    }

  }

}