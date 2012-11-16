package es.rubenromero.academic.katas

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import StringCalculator._

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
}