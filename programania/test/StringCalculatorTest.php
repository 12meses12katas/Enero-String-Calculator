<?php

require_once dirname(__FILE__) . '/../StringCalculator.php';

class StringCalculatorTest extends PHPUnit_Framework_TestCase
{

    public function provider()
    {
        return array(
            '""        should return 0' => array('', 0)
            , '"1"       should return 1' => array('1', 1)
            , '"1,1"     should return 2' => array('1,1', 2)
            , '"1,1,1"   should return 3' => array('1,1,1', 3)
            , '"1,2,3,4,5" should return 15' => array('1,2,3,4,5', 15)
            , '"1\n2,3"  should return 6' => array('1\n2,3', 6)
            , '"//;\n1;2" shound return 3' => array('//;\n1;2', 3)
            , '"//;\n1;1000" shound return 1' => array('//;\n1;1000', 1)
            , " “//[***]\n1***2***3” should return 6" => array('//[***]\n1***2***3', 6)
            , "//[*][%][rtrt][%==][;;;]\n1*2%3” should return 6" => array('//[*][%]\n1*2%3', 6)
        );
    }

    /**
     * @dataProvider provider
     */
    public function testStringCalculator($cadena, $expected)
    {
        $calculator = new StringCalculator();

        $this->assertEquals($expected, $calculator->add($cadena));
    }

    public function testStringCalculatorException()
    {
        $this->setExpectedException("Exception");

        $calculator = new StringCalculator();

        $calculator->add("-3,1");
    }

}