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
  }
}

object StringCalculator {
  def add(input: String): Integer = {
    if (isEmpty(input)) return 0
    else return input.toInt
  }

  def isEmpty(input: String): Boolean = input.size == 0
}
