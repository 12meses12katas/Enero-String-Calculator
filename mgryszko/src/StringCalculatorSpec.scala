import org.scalatest.matchers.MustMatchers
import org.scalatest.Spec

class StringCalculatorSpec extends Spec with MustMatchers {
  private val calculator = new StringCalculator

  describe("String Calculator") {
    describe("when an empty string is passed") {
      it("yields 0") {
        calculator.sum("") must equal (0)
      }
    }

    describe("when a string with 1 number is passed") {
      it("yields the number") {
        calculator.sum("1") must equal (1)
      }
    }

    describe("when a string with 2 numbers separated by comma is passed") {
      it("yields the sum of the 2 numbers") {
        calculator.sum("1,2") must equal (3)
      }
    }

    describe("when a string with 3 numbers separated by comma is passed") {
      it("yields the sum of the 3 numbers") {
        calculator.sum("1,2,3") must equal (6)
      }
    }

    describe("when a string with 3 numbers separated by new line and comma is passed") {
      it("yields the sum of the 3 numbers") {
        calculator.sum("1,2\n3") must equal (6)
      }
    }

    describe("when a string with 3 numbers separated by a custom single char separator is passed") {
      it("yields the sum of the 3 numbers") {
        calculator.sum("//[;]\n1;2;3") must equal (6)
      }
    }

    describe("when a string with negative number is passed") {
      it("throws an exception") {
        val thrown = evaluating { calculator.sum("-1,2,-3") } must produce [IllegalArgumentException]
        thrown.getMessage must equal ("-1,-3")
      }
    }

    describe("when a string with a number >= 1000 is passed") {
      it("yields the sum ingoring that number") {
        calculator.sum("1,1001,2") must equal (3)
      }
    }

    describe("when a string with 3 numbers separated by a custom single string separator is passed") {
      it("yields the sum of the 3 numbers") {
        calculator.sum("//[***]\n1***2***3") must equal (6)
      }
    }

    describe("when a string with 3 numbers separated by custom multiple string separators is passed") {
      it("yields the sum of the 3 numbers") {
        calculator.sum("////[**][%%]\n1**2%%3") must equal (6)
      }
    }
  }
}