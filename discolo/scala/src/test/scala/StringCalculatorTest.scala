

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import StringCalculator._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StringCalculatorTest extends FunSuite {

  test("add empty string") {
    assert(add("") === 0)
  }

  test("add one number") {
    assert(add("3") === 3)
  }
  
  test("add two numbers") {
    assert(add("4,5") === 4+5)
  }
    
  test("add any numbers") {
    assert(add("4,5,6,7,8,9") === 4+5+6+7+8+9)
  }
  
  test("new lines as delimiters") {
    assert(add("5,6\n7") === 5+6+7)
  }
  
  test("new lines as delimiters - invalid case") {
    try {
      add("5,6\n,7")
      fail()
    } catch {
      case e: NumberFormatException => assert(true)
    }
  }
  
  test("set new delimiter") {
    assert(add("//;\n9;99;999") === 9+99+999)
  }
  
  test("negatives not allowed") {
    try {
      add("5,-6\n7")
      fail()
    } catch {
      case e: Error => assert(true)
    }
  }
  
  test("numbers greater than 1000 are ignored") {
    assert(add("5,1001,6") === 5 + 6)
  }
  
  test("number 1000 is not ignored") {
    assert(add("5,1000,6") === 5 + 1000 + 6)
  }
  
  test("multilength delimiter") {
    assert(add("//[aaa]\n4aaa5aaa1001aaa4") === 4 + 5 + 4)
  }
  
  test("multilength delimiter with special characters") {
    assert(add("//[_*_]\n4_*_5_*_1001_*_4") === 4 + 5 + 4)
  }
  
  test("multilength multiple delimiter") {
    assert(add("//[_*_][***]\n4_*_5***1001_*_4") === 4 + 5 + 4)
  }
}