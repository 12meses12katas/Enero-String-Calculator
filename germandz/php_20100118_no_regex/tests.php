<?php
require_once "PHPUnit/Extensions/Story/TestCase.php";

require("string_calculator.php");

class StringCalculatorTest extends PHPUnit_Framework_TestCase
{
    
    public function setup() {
        $this->calculator = new StringCalculator();
    }
    
    public function testEmptyString() {
        $this->assertThat($this->calculator->add(""), $this->equalTo(0));
    } 

    public function testOneNumber() {
        $this->assertThat($this->calculator->add("7"), $this->equalTo(7));
    } 

    public function testTwoNumbers() {
        $this->assertThat($this->calculator->add("4,5"), $this->equalTo(9));
    }
    
    public function testAnyQtyOfNumbers() {
        $this->assertThat($this->calculator->add("4,5,3,7"), $this->equalTo(19));
    }
    
    public function testNewLineAlsoSeparatesNumbers() {
        $this->assertThat($this->calculator->add("3\n4,2"), $this->equalTo(9));
    }
    
    public function testCustomDelimiter() {
        $this->assertThat($this->calculator->add("//;\n10;3"), $this->equalTo(13));
    }
    
    /**
     * @expectedException InvalidArgumentException
     */
    public function testNegativesThrowsException() {
        $this->calculator->add("6,-3");
    }
    
    public function testNegativesAreReported() {
        try {
            $this->calculator->add("6,-3,8,-5,3");
        }
        catch (InvalidArgumentException $negatives_exception) {
            $exception_message = $negatives_exception->getMessage();
            $this->assertThat($exception_message, $this->stringContains("negatives not allowed"));
            $this->assertThat($exception_message, $this->stringContains("-3"));
            $this->assertThat($exception_message, $this->stringContains("-5"));
            $this->assertThat($exception_message, $this->logicalNot($this->stringContains("6")));
        }
    }
    
    public function testMoreThan1000NotAdded() {
        $this->assertThat($this->calculator->add("3,5,1001,1"), $this->equalTo(9));
    }
    
    public function testCustomDelimiterAnyLength() {
        $this->assertThat($this->calculator->add("//[***]\n10***3"), $this->equalTo(13));
    }

    public function testMultipleCustomDelimiter() {
        $this->assertThat($this->calculator->add("//[*][%]\n1*2%3"), $this->equalTo(6));
        $this->assertThat($this->calculator->add("//[*][%%%]\n1*2%%%3"), $this->equalTo(6));
    }

}