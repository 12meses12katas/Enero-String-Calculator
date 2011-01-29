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
    
}

class StringCalculator {

    int add(String numbers) {
        if (numbers == " ") {
            0
        }
        else {
            numbers.split(",").inject(0) { sum, number ->
                sum += number.toInteger()
            }
        }
    }
    
}
