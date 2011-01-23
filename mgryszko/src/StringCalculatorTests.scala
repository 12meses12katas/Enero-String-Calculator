import org.junit.{Before, Test}
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import org.hamcrest.Matchers._

class StringCalculatorTests extends JUnitSuite {
  private val calculator = new StringCalculator

  @Test
  def emptyString_sumsAs_0() {
    assertThat(calculator.sum(""), equalTo(0))
  }

  @Test
  def stringWithOneNumber_sumsAs_theNumber() {
    assertThat(calculator.sum("1"), equalTo(1))
  }

  @Test
  def twoNumbersSeparatedWithComma_sumAs_twoNumbers() {
    assertThat(calculator.sum("1,2"), equalTo(3))
  }

  @Test
  def threeNumbersSeparatedWithComma_sumAs_threeNumbers() {
    assertThat(calculator.sum("1,2,3"), equalTo(6))
  }

  @Test
  def threeNumbersSeparatedWithNewLineAndComma_sumAs_threeNumbers() {
    assertThat(calculator.sum("1,2\n3"), equalTo(6))
  }

  @Test
  def threeNumbersSeparatedWithCustomSingleCharSeparator_sumAs_threeNumbers() {
    assertThat(calculator.sum("//;\n1;2;3"), equalTo(6))
  }

  @Test
  def negativeNumberInString_causes_exception() {
    val e = intercept[IllegalArgumentException] {
      calculator.sum("-1,2,-3")
    }
    assertThat(e.getMessage(), equalTo("-1,-3"))
  }

  @Test
  def numbersGreaterThat1000_areIgnored() {
    assertThat(calculator.sum("1,1001,2"), equalTo(3))
  }

  @Test
  def threeNumbersSeparatedWithCustomMultiCharSeparator_sumAs_threeNumbers() {
    assertThat(calculator.sum("//[***]\n1***2***3"), equalTo(6))
  }

  @Test
  def threeNumbersSeparatedWithCustomMultipleMultiCharSeparators_sumAs_threeNumbers() {
    assertThat(calculator.sum("//[**][%%]\n1**2%%3"), equalTo(6))
  }
}