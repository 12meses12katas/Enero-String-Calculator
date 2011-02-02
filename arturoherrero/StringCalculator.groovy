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
        assert stringCalculator.add("//;\n1;2") == 3
        assert stringCalculator.add("//+\n1+2") == 3
        assert stringCalculator.add("//s\n1s2") == 3
    }
    
    void testStringNumbersNegativesNotAllowed() {
        def message = shouldFail(Exception) {
            stringCalculator.add("1,-1,2,-2")
        }
        assert message.contains("negatives not allowed")
        assert message.contains("-1")
        assert message.contains("-2")
    }
    
    void testIgnoreBiggerNumbers() {
        assert stringCalculator.add("2,1001") == 2
    }
    
}

class StringCalculator {

    int add(String numbers) {
        numbers = changeDelimiterToComma(numbers)
        getNumbersInList(numbers).inject(0) { sum, num ->
            if (isPositive(num))
                if (isBigger(num))
                    sum += 0
                else
                    sum += num.toInteger()
            else
                negativesNotAllowed(numbers)
        }
    }
    
    private String changeDelimiterToComma(String numbers) {
        numbers.find(/(?s)\/\/(.+)\n/) { match ->
            def delimeter = match[1]
            numbers = numbers.minus(match[0]).replace(delimeter, ",")
        }
        return numbers
    }
    
    private getNumbersInList(String numbers) {
        numbers.replaceAll(" ", "0").replaceAll("\n", ",").split(",")
    }
    
    private isPositive(String num) {
        num.toInteger() >= 0
    }
    
    private isNegative(String num) {
        num.toInteger() < 0
    }
    
    private isBigger(String num) {
        num.toInteger() > 1000
    }
    
    private negativesNotAllowed(String numbers) {
        def negativeNums = []
        getNumbersInList(numbers).each { num ->
            if (isNegative(num))
                negativeNums << num
        }
        throw new Exception("negatives not allowed " + negativeNums)
    }
    
}

