import groovy.util.GroovyTestCase

class StringCalculatorTest extends GroovyTestCase {

    def stringCalculator = new StringCalculator()

    void testEmptyStringReturnZero() {
        assert stringCalculator.add(" ") == 0
    }

    void testStringReturnNumber() {
        assert stringCalculator.add("1") == 1
    }

    void testTwoStringReturnTheirSum() {
        assert stringCalculator.add("1,2") == 3
    }
    
    void testStringNumbersReturnTheirSum() {
        assert stringCalculator.add("1,2,3") == 6
    }
    
    void testStringNumbersWithNewLinesReturnTheirSum() {
        assert stringCalculator.add("1\n2,3") == 6
    }
    
    void testStringNumbersWithDelimiterReturnTheirSum() {
        assert stringCalculator.add("//;\n1\n2") == 3
    }
    
}

class StringCalculator {

    int add(String numbers) {
        numbers = changeDelimiterToComma(numbers)
        numbers.replaceAll(" ", "0").replaceAll("\n", ",").split(",").inject(0) { sum, number ->
            sum += number.toInteger()
        }
    }
    
    private String changeDelimiterToComma(String numbers) {
        numbers.find(/\/\/\S[\n]/) { match ->
            def delimeter = match[2]
            numbers = numbers.minus(match).replaceAll(delimeter, ",")
        }
        return numbers
    }
    
}

