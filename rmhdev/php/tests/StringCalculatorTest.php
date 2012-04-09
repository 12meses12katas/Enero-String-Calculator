<?php

require '../StringCalculator.php';

class StringCalculatorTest extends PHPUnit_Framework_TestCase {

    function testEmptyStringShouldRetunZero(){
        $this->assertEquals(0, StringCalculator::sum(""));
    }

    function testSingleNumberShouldReturnNumber(){
        $this->assertEquals(1, StringCalculator::sum("1"));
    }

    function testStringWithTwoNumbersShouldReturnSumOfBoth(){
        $this->assertEquals(3, StringCalculator::sum("1,2"));
    }

    function testStringWithMultipleNumbersShouldReturnSumOfAll(){
        $this->assertEquals(1 + 2 + 3 + 4 + 5, StringCalculator::sum("1,2,3,4,5"));
    }

    function testStringWithNumbersAndNewLinesBetweenNumbersShouldReturnSumOfAll(){
        $this->assertEquals(1 + 2 + 3, StringCalculator::sum("1\n2,3"));
    }

    function testSupportDifferentDelimitersShouldReturnSumOfAll(){
        $this->assertEquals(1 + 2, StringCalculator::sum('//;\n1;2'));
    }

    function testStringWithNegativeNumbersShouldThrowExceptionNoticingAllNegatives() {
        $this->setExpectedException("NegativesNotAllowed", "-1,-2,-3");
        StringCalculator::sum("1,-1,2,-2,3,-3");
    }

    function testStringWithNumbersGreaterThan1000ShouldIgnoreThem(){
        $this->assertEquals(5, StringCalculator::sum("2,1001,3"));
    }

    function testStringWithLongDelimiterShouldReturnSumOfAllNumbers(){
        $this->assertEquals(6, StringCalculator::sum('//[***]\n1***2***3'));
    }

    function testStringWithMultipleDelimitersShouldReturnSumOfAllNumbers(){
        $this->assertEquals(6, StringCalculator::sum('//[*][%]\n1*2%3'));
    }

    function testStringWithMultipleLongDelimitersShouldReturnSumOfAllNumbers(){
        $this->assertEquals(6, StringCalculator::sum('//[***][%%%]\n1***2%%%3'));
    }
}