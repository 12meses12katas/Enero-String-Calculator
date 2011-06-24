<?php

require_once 'StringCalculator.php';

class StringCalculatorTest extends PHPUnit_Framework_TestCase {
    public function testFramework() {
        $this->assertTrue(true);
    }

    public function testEmptyStringShouldReturnZero() {
        $stringCalculator = new stringCalculator();
        $actual = $stringCalculator->add('');
        $this->assertEquals(0, $actual);
    }

    public function testOneNumberShouldReturnThatNumber() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add('5');
        $this->assertEquals(5, $actual);
    }

    public function testTwoNumbersShouldReturnSumOfThatNumbers() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add('5,4');
        $this->assertEquals(9, $actual);
    }

    public function testHugeAmountOfNumbersShouldReturnSumOfAllNumbers() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add('5,4,1,10,4,89,42');
        $this->assertEquals(155, $actual);
    }

    public function testTwoNumberNewLineSeparatedShouldReturnNumbersSum() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("5\n4,4");
        $this->assertEquals(13, $actual);
    }

    public function testOneNumberWithTwoSeparatorsNotOk() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("5\n,");
        $this->assertEquals(NULL, $actual);
    }

    public function testChangingDelimiter() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("//;\n1;2");
        $this->assertEquals(3, $actual);
    }

    public function testDelimiterCanBeAnythingWithoutStatingItBefore() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("1;2");
        $this->assertEquals(3, $actual);
    }

    public function testNegativeNumbersShouldThrowAnException() {
        try {
            $stringCalculator = new StringCalculator();
            $actual = $stringCalculator->add("//;\n1;2;-4");
        } catch (Exception $exception) {
            if ($exception->getMessage() == 'Negatives not allowed: -4')
            return;
        }
        $this->fail('error');
    }

    public function testMultipleNegativeNumbersShouldThrowAnExceptionIncludingNegativeNumbers() {
        try {
            $stringCalculator = new StringCalculator();
            $actual = $stringCalculator->add("//;\n1;2;-4;-10");
        } catch (Exception $exception) {
            if ($exception->getMessage() == 'Negatives not allowed: -4 -10')
            return;
        }
        $this->fail('error');
    }

    public function testNumbersGreaterThan1000ShouldBeIgonred() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("//;\n1;2;1040;10344");
        $this->assertEquals(3, $actual);
    }

    public function testDelimitersOfAnyLength() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("//[+++]\n1+++2+++1040+++10344");
        $this->assertEquals(3, $actual);
    }

    public function testDelimitersUtfOfAnyLength() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("//[ñññ]\n1ñññ2ñññ1040ñññ10344");
        $this->assertEquals(3, $actual);
    }

    public function testMultipleDelimiters() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("//[ñ][ü]\n1ñ2ü1040ñ10344");
        $this->assertEquals(3, $actual);
    }

    public function testMultipleDelimitersLongerThanOneChar() {
        $stringCalculator = new StringCalculator();
        $actual = $stringCalculator->add("//[ññ][éé]\n1ññ2éé1040ññ10344");
        $this->assertEquals(3, $actual);
    }

}


?>
