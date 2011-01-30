package micubiculo

import org.junit.runner.RunWith
import org.specs.runner.JUnitSuiteRunner
import org.specs.SpecificationWithJUnit

@RunWith(classOf[JUnitSuiteRunner])
class StringCalculatorSpec extends SpecificationWithJUnit {

  "String calculator" should {
    "return 0 in case the input is an empty string" in {
      StringCalculator.add("") must beEqualTo(0)
    }

    "return 3 in case the input '3'" in {
      StringCalculator.add("3") must beEqualTo(3)
    }

    "return 12 in case the input '12'" in {
      StringCalculator.add("12") must beEqualTo(12)
    }
    
    "return the sum in case two number are given" in {
      StringCalculator.add("12,5") must beEqualTo(17)
    }
    
    "return the sum in case four number are given" in {
      StringCalculator.add("12,5,1,1") must beEqualTo(19)
    }
  }
}


