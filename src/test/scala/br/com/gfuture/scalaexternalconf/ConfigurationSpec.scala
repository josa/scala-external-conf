package br.com.gfuture.scalaexternalconf

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{Spec, BeforeAndAfterEach}

class ConfigurationSpec extends Spec with ShouldMatchers with BeforeAndAfterEach {

  describe("Configuration"){

    it("should load one property from file"){
      Configuration.get("br.com.property1") should equal("property1")
    }

    it("should load other property from file"){
      Configuration.get("br.com.property2") should equal("property2")
    }

  }

}