<?php
include "../StringCalculator.php";
class StringCalculatorTest extends PHPUnit_Framework_TestCase
{
    protected $stringCalculator;
    public function setUp()
    {
        $this->stringCalculator = new StringCalculator;
    }

    public function getDataProvider()
    {
        return array(
            'returns 0 when nulls'      => array('expected' => 0, 'value' => ""),
            'returns 1 when "1"'        => array('expected' => 1, 'value' => "1"),
            'returns 2 when "1,1"'      => array('expected' => 2, 'value' => "1,1"),
            'returns 2 when "1,1001"'   => array('expected' => 1, 'value' => "1,1001"),
            'returns 6 when "1,2,3"'    => array('expected' => 6, 'value' => "1,2,3"),
            'returns 10 when "5,2,3"'   => array('expected' => 10, 'value' => "5,2,3"),
            'returns 3 when "//\n\n1\n2\n"' => array('expected' => 3, 'value' => "//\n\n1\n2\n"),
            'returns 6 when "1\n2,3"'   => array('expected' => 6, 'value' => "1\n2,3"),
            'returns 5 when "//x\n3x2"' => array('expected' => 5, 'value' => "//x\n3x2"),
            'custom separator'          => array('expected' => 6, 'value' => "//*\n1*2*3"),
            'custom separator2'         => array('expected' => 6, 'value' => "//]\n1]2]3"),
            'custom separator multi'    => array('expected' => 6, 'value' => "//[***]\n1***2***3"),
            'custom separators'         => array('expected' => 6, 'value' => "//[*][%]\n1*2%3"),
            'custom separators multi'   => array('expected' => 6, 'value' => "//[**][%]\n1**2%3"),
            );
    }

    public function getDataProviderDecodeString()
    {
        return array(
            "test simple separator"  => array('in' => "//x\n3x2", 'string' => "3,2"),
            "test simple separator1" => array('in' => "//+\n1+3", 'string' => "1,3"),
            "test simple separator2" => array('in' => "//*\n1*3", 'string' => "1,3"),

            "test simple separator multibyte" => array(
                'in'        => "//[**]\n1**3", 
                'string'    => "1,3"),
            "test simple separator multibyte2" => array(
                'in'        => "//[+++]\n1+++3", 
                'string'    => "1,3"),

            "test multiple separators" => array(
                'in' => "//[*][%]\n1*2%3",
                'string' => "1,2,3"
                )
            );
    }
    
    /**
     * @dataProvider getDataProvider
     */
    public function testAdd($expected, $value)
    {
        $this->assertEquals($expected, $this->stringCalculator->add($value));
    }

    /**
     * @expectedException InvalidArgumentException
     */
    public function testOneNegative()
    {
        $this->stringCalculator->add("-1");
    }

    public function testMultipleNegative()
    {
        try {
            $this->stringCalculator->add("-1,-2,3");
        } catch (InvalidArgumentException $e){
            $this->assertEquals("negatives not allowed. -1, -2", $e->getMessage());
        }
    }

    /**
     * @dataProvider getDataProviderDecodeString
     */
    public function testDecodeString($in, $string)
    { 
        $this->assertEquals($string, $this->stringCalculator->decodeString($in));
    }

}
