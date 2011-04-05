package test.scala.br.com.gfuture.scalaexternalconf

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{AbstractSuite, Spec, BeforeAndAfterEach}

abstract class ConfigurationSpec  extends Spec with ShouldMatchers with BeforeAndAfterEach {

  describe("Configuration"){

    it("should load on property"){

      Configuration.properties

    }

  }

}