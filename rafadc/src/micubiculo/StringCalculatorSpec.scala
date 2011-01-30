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

    "return the sum in case two number are given separated with a \n" in {
      StringCalculator.add("12\n5") must beEqualTo(17)
    }

    "return the sum in case we use different separators" in {
      StringCalculator.add("12,1\n5") must beEqualTo(18)
    }

    "the default delimiter is changed when first line starts with //<delimiter>" in {
      StringCalculator.add("//;\n3;5") must beEqualTo(8)
    }

    "the default delimiter is changed and we can mix with \n" in {
      StringCalculator.add("//;\n3;5\n2") must beEqualTo(10)
    }

    "an exception must be thrown when negative number is passed" in {
      StringCalculator.add("-1") must throwA(new RuntimeException("negative numbers not allowed: -1"))
    }

    "an exception must be thrown when negative number is passed" in {
      StringCalculator.add("-3") must throwA(new RuntimeException("negative numbers not allowed: -3"))
    }
    
    "an exception must be thrown when a list of negative number are passed" in {
      StringCalculator.add("-3,1,-4") must throwA(new RuntimeException("negative numbers not allowed: -3 -4"))
    }
  }
}

