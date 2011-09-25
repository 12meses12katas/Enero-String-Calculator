<?php
require_once 'PHPUnit/Autoload.php';
require_once 'src/StringParser.php';

class StringParserTest extends PHPUnit_Framework_TestCase
{
    private $stringParser;
    
    
    public function SetUp()
    {
        $this->stringParser = new StringParser();
    }
    
    public function testFramework()
    {
        $this->assertTrue(true);
    }
    
    public function testDescomposeNumbersSeparatedWithLines()
    {
        $this->assertEquals(array('1','2','3'),                    $this->stringParser->parse("1\n2,3"));
        $this->assertEquals(array('54','2','5', '7','9','11','8'), $this->stringParser->parse("54,2\n5,7\n9\n11,8"));
    }

    public function testDescomposeNumbersWithCustomDelimiter()
    {
        $this->assertEquals(array('1','2'), $this->stringParser->parse("//;\n1;2"));
        $this->assertEquals(array('1','2','8'), $this->stringParser->parse("//;\n1;2;8"));
    }
    
    /**
     * @expectedException        InvalidArgumentException
     * @expectedExceptionMessage negatives not allowed -> -2
     */
    public function testExceptionIfAddNegativeNumbers()
    {
        $this->stringParser->parse('1,-2');
    }
    
    /**
     * HELPER TESTS for development. No need anymore.
     */
/*
    public function testCheckNegatives()
    {
        $this->assertEquals(array('1','2'),         $this->stringParser->checkNegatives(array('1','2')));
        $this->assertEquals(array('1','1001', '8'), $this->stringParser->checkNegatives(array('1','1001', '8')));
    }
    
    /**
     * @expectedException        InvalidArgumentException
     * @expectedExceptionMessage negatives not allowed -> -2
     */
/*    
    public function testCheckNegativesException()
    {
        $this->stringParser->checkNegatives(array('1','-2'));
    }

    public function testRemoveGreatherThan1000()
    {
        $this->assertEquals(array('2'), $this->stringParser->removeGreaterThan1000(array('2', '1001')));
        $this->assertEquals(array('2','8'), $this->stringParser->removeGreaterThan1000(array('2', '1001', '8')));
        $this->assertEquals(array('2','8'), $this->stringParser->parse("//;\n2;1001;8"));
    }
    
    public function testDelimitersCouldBeAnyLength()
    {
        $this->assertEquals('***', $this->stringParser->recoverCustomDelimiters("//[***]\n1***2***3"));
    }

    public function testDelimitersCouldBeMultiple()
    {
        $this->assertEquals('*%', $this->stringParser->recoverCustomDelimiters("//[*][%]\n1*2%3"));
    }
*/
}
