<?php

class StringCalculatorTest extends PHPUnit_Framework_TestCase {

    private $calculator;

    public function setUp(){
        $this->calculator = new StringCalculator(new Parser(), new Validator());
    }

    public function dataProvider() {
        return array(
            "whenTheStringIsEmptyReturnsZero" => array(0, ""),
            "whenIsANumberReturnsThatNumber" => array(1, "1"),
            "sumWhenAreMoreArguments" => array(3, "1,2"),
            "sumWhenAreMoreArgumentsAndMoreDelimiters" => array(6, "1\n2,3"),
            "whenWeDefinedDelimiterInTheStartOfString" => array(3, "//;\n1;2"),
            "numbersBiggerThanThousandShouldBeIgnored" => array(1, "1,1000"),
            "delimitersCanBeOfAnyLength" => array(6,"/[***]\n1***2***3"),
            "allowMultipleDelimiters" => array(6,"//[*][%]\n1*2%3"),
        );
    }

    /**
     * @dataProvider dataProvider
     * @test
     */
    public function sumaDeNumerosEnCadena($expected, $string) {
        $this->assertEquals($expected, $this->calculator->add($string));
    }

    /**
     * @test
     * @expectedException InvalidArgumentException
     */
    public function noSeAceptanNumerosNegativos(){
       $this->calculator->add("1,-2");
    }

}

?>
