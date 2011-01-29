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
    
}

class StringCalculator {

    int add(String numbers) {
        if (numbers == " ") {
            return 0
        }
        else {
            def sum = 0
            numbers.split(",").each {
                sum += it.toInteger()
            }
            return sum
        }
    }
    
}
