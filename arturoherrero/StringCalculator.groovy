/**
 *   kata:    String Calculator http://www.osherove.com/tdd-kata-1/
 *   Author:  Arturo Herrero
 *   Web:     http://arturoherrero.com/
 *   Twitter: @ArturoHerrero
 */

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
    
    void testDelimitersCanBeAnyLength() {
        assert stringCalculator.add("//[***]\n1***2***3") == 6
    }
    
    void testMultipleDelimiters() {
        assert stringCalculator.add("//[*][%]\n1*2%3") == 6
    }
    
    void testMultipleDelimitersCanBeAnyLength() {
        assert stringCalculator.add("//[**][%%]\n1**2%%3") == 6
    }
    
}

class StringCalculator {

    int add(String numbers) {
        numbers = changeDelimitersToComma(numbers)
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
    
    private String changeDelimitersToComma(String numbers) {
        def delimeters = ["\n"]
        numbers.find(/(?s)\/\/(.+)\n/) { match ->
            delimeters << match[1]
            match[1].eachMatch(/\[(.+?)\]/) { subMatch ->
                delimeters << subMatch[1]
            }
            numbers = numbers.minus(match[0])
        }  
              
        delimeters.each {
            numbers = numbers.replace(it, ",")
        }        
        return numbers
    }
    
    private getNumbersInList(String numbers) {
        numbers.replaceAll(" ", "0").split(",")
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

