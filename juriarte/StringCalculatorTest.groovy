import groovy.util.GroovyTestCase

class StringCalculatorTest extends GroovyTestCase {

  def calculator = new StringCalculator()

  void testNoNumbersReturnsZero() {
    assert calculator.add("") == 0
  }

  void testOneNumberReturnsItself() {
    ["1","4","5","6"].each {
      assert calculator.add(it).toString() == it
    }
  }

  void testTwoNumbersGetAdded() {
    ["1,2":3, "3,5":8, "9,9":18].each { input, output ->
      assert calculator.add(input) == output
    }
  }

  void testSeveralNumbers() {
    ["1,2,34":37, "9,8,7,6":30].each { inp, out ->
      assert calculator.add(inp) == out
    }
  }

  void testNumbersWithNewLineAsSeparator() {
    ["1\n2,4":7].each { inp, out ->
      assert calculator.add(inp) == out
    }
  }

  void testConfigurabilityOfSeparator() {
    ["//;\n2;4":6, "//+\n3+201":204].each { inp, out ->
      assert calculator.add(inp) == out
    }
  }

  void testNegativesNotAllowed() {
    ["1,-3,-5,6,-8":["-3", "-5", "-8"],
     "-1,-3"       :["-1", "-3"],
     "-1"          :["-1"],
     "1,2,3,-1"    :["-1"]
    ].each { test, values ->
      try {
        calculator.add(test)
        assert false
      } catch (e) {
        assert e.message.contains("negatives not allowed")
        values.each { v ->
          assert e.message.contains(v)
        }
      }
    }
  }

  void testBiggerThanThousendIgnored() {
    ["2,1001":2].each { inp, out ->
      assert calculator.add(inp) == out
    }
  }

  void testBigDelimiters() {
    ["//[add]\n123add243":366].each { inp, out ->
      assert calculator.add(inp) == out
    }
  }

  void testBigDelimitersWithSpecialRegexpChars() {
    ["//[***]\n123***243":366,
     "//[***][+++]\n111***222+++333":666,
     "//[*+*][+**+]\n111*+*222*+*333+**+111":777,
     "//[?]\n111?222?333?111":777].each { inp, out ->
      assert calculator.add(inp) == out
    }
  }

  void testMultipleDelimiters() {
    ["//[add][pepe][juan]\n1add2juan1pepe3":7,
     "//[add][pepe][juan]\n1a2a1a3":"FALLO1",
     "//[add][pepe][juan]\n20add30juan40pepe50":140,
    ].each { inp, out ->
      try {
        assert calculator.add(inp) == out
      } catch (e) {
        assert e instanceof NumberFormatException
      }
    }
  }

}

