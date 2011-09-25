<?php
require_once 'PHPUnit/Autoload.php';
require_once 'src/StringCalculator.php';

class StringCalculatorTest extends PHPUnit_Framework_TestCase
{
    private $stringCalculator;
    
    
    public function SetUp()
    {
        $this->stringCalculator = new StringCalculator(new StringParser());
    }
    
	public function testFramework()
    {
    	$this->assertTrue(true);
    }
    
    public function testEmptyStringShouldBeZero()
    {
        $this->assertEquals('0', $this->stringCalculator->add(''));
    }
    
    public function testAddOneNumberShouldBeTheNumber()
    {
        $this->assertEquals('0',  $this->stringCalculator->add('0'));
        $this->assertEquals('1',  $this->stringCalculator->add('1'));
        $this->assertEquals('53', $this->stringCalculator->add('53'));
    }
    
    public function testAddTwoNumbersShouldBeTheSumOfTheNumbers()
    {
        $this->assertEquals('1',   $this->stringCalculator->add('0,1'));
        $this->assertEquals('3',   $this->stringCalculator->add('1,2'));
        $this->assertEquals('100', $this->stringCalculator->add('53,47'));
    }
    
    public function testAddThreeNumbersShouldBeTheSumOfTheNumbers()
    {
        $this->assertEquals('3',   $this->stringCalculator->add('0,1,2'));
        $this->assertEquals('11',  $this->stringCalculator->add('1,2,8'));
        $this->assertEquals('105', $this->stringCalculator->add('53,47,5'));
    }
    
    public function testAddUnknowAmountOfNumbersShouldBeTheSumOfTheNumbers()
    {
        $this->assertEquals('10',  $this->stringCalculator->add('0,1,2,5,2'));
        $this->assertEquals('130', $this->stringCalculator->add('1,2,8,34,23,2,55,1,4'));
        $this->assertEquals('188', $this->stringCalculator->add('53,47,5,1,4,6,33,6,33'));
    }
    
    public function testHandleNewLinesOrCommasAsSeparators()
    {
        $this->assertEquals('6',  $this->stringCalculator->add("1\n2,3"));
        $this->assertEquals('15', $this->stringCalculator->add("1\n2,3,7\n2"));
        $this->assertEquals('96', $this->stringCalculator->add("54,2\n5,7\n9\n11,8"));
    }
    
    public function testAddNumbersWithCustomDelimiter()
    {
        $this->assertEquals('3',  $this->stringCalculator->add("//;\n1;2"));
        $this->assertEquals('15', $this->stringCalculator->add("//|\n1|2|12"));
    }
    
    /**
     * @expectedException        InvalidArgumentException
     * @expectedExceptionMessage negatives not allowed -> -2 
     */
    public function testExceptionIfAddNegativeNumbers()
    {
        $this->stringCalculator->add('1,-2');
    }

    /**
     * @expectedException        InvalidArgumentException
     * @expectedExceptionMessage negatives not allowed -> -2, -5, -8, -6, -6
     */
    public function testExceptionIfAddSeveralNegativeNumbers()
    {
        $this->stringCalculator->add('1,-2,-5,6,-8,-6,-6');
        $this->stringCalculator->add('//|\n1|-2|-5|6|-8|-6|-6');
    }
    
    public function testNumbersBiggerThan1000ShouldBeIgnored()
    {
        $this->assertEquals('2',  $this->stringCalculator->add('2,1001'));
        $this->assertEquals('10', $this->stringCalculator->add("//;\n2;1001;8"));
    }
    
    public function testDelimitersCouldBeAnyLength()
    {
        $this->assertEquals('6', $this->stringCalculator->add("//[***]\n1***2***3"));
    }
    
    public function testDelimitersCouldBeMultiple()
    {
        $this->assertEquals('6', $this->stringCalculator->add("//[*][%]\n1*2%3"));
        $this->assertEquals('6', $this->stringCalculator->add("//[*][%]\n1*2%3*1001"));
        $this->assertEquals('8', $this->stringCalculator->add("//[*][%]\n1*2%1001%3*2"));
    }
    
    public function testDelimitatorsWithAnyLengthCouldBeMultiple()
    {
        $this->assertEquals('8', $this->stringCalculator->add("//[**][%]\n1**2%1001%3**2"));
        $this->assertEquals('8', $this->stringCalculator->add("//[*][%%%]\n1*2%%%1001%%%3*2"));
    }
}
