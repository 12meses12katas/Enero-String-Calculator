<?php

include_once "../StringCalculator.php";

class StringCalculatorTest extends PHPUnit_framework_TestCase {

    protected $sc = null;

    public function setUp() {
        $this->sc = new StringCalculator();
    }

    public function dataProvider() {
        return array(
            "sumaVaciaDevuelveCero" => array(0, ""),
            "sumaUnSoloNumero1" => array(1, "1"),
            "sumaUnSoloNumero2" => array(332, "332"),
            "sumaDosNumeros1" => array(2, "1,1"),
            "sumaDosNumeros2" => array(22, "10,12"),
            "sumaVariosNumeros1" => array(6, "1,2,3"),
            "sumaVariosNumeros2" => array(60, "10,20,30"),
            "sumaVariosNumerosMasDeMil" => array(460, "10,20,30,400,1000"),
            "sumaConRetornosYComas1" => array(6, "1\n2,3"),
            "sumaConRetornosYComas2" => array(3, "1\n2"),
            "sumaConfigurandoDelimitador1" => array(3, "//;\n1;2"),
            "sumaConfigurandoDelimitador2" => array(7, "//;\n1;2;3\n1"),
            "sumaConfigurandoDelimitadoLargo1" => array(6, "//[***]\n1***2***3"),
            "sumaConfigurandoDelimitadoLargo2" => array(6, "//[+*+]\n1+*+2+*+3"),
            "sumaConfigurandoDelimitadorMultiple" => array(3, "//;\n1;2"),
        );
    }

    /**
     * @dataProvider dataProvider
     */
    public function testSumaString($suma,$string) {
        $this->assertEquals($suma,$this->sc->add($string));
    }

    /**
     *  @expectedException InvalidArgumentException
     */
    public function testExpcepcionNegativa() {
        $this->sc->add('1,-2,3');
    }




}

?>
