<?php

include_once "../StringCalculator.php";

class StringCalculatorTest extends PHPUnit_framework_TestCase {

    protected $sc = null;

    public function setUp() {
        $this->sc = new StringCalculator();
    }

    public function testFramework() {
        $this->assertTrue(true);
    }

    public function testSumaVacio() {
        $this->assertEquals($this->sc->add(''), 0);
    }

    public function testSumaUnSoloNumero() {

        $this->assertEquals($this->sc->add('1'), 1);
        $this->assertEquals($this->sc->add('332'), 332);
    }

    public function testSumaDosNumeros() {
        $this->assertEquals($this->sc->add('1,1'), 2);
        $this->assertEquals($this->sc->add('10,12'), 22);
    }

    public function testSumaVariosNumeros() {
        $this->assertEquals($this->sc->add('1,2,3'), 6);
        $this->assertEquals($this->sc->add('10,20,30'), 60);
        $this->assertEquals($this->sc->add('10,20,30,400,1000'), 460);
    }

    public function testRetornosComas() {
        $this->assertEquals($this->sc->add("1\n2"), 3);
        $this->assertEquals($this->sc->add("1,2\n3"), 6);
    }

    public function testExpcepcionNegativa() {

        try {
            $this->sc->add('1,-2,3');
        } catch (Exception $exception) {

        }
    }

    public function testCambioDelimitador() {
        $this->assertEquals($this->sc->add("//;\n1;2"), 3);
        $this->assertEquals($this->sc->add("//;\n1;2;3\n1"), 7);
    }

    public function testCambioDelimitadorLargo() {
        $this->assertEquals($this->sc->add("//[***]\n1***2***3"), 6);
        $this->assertEquals($this->sc->add("//[+*+]\n1+*+2+*+3"), 6);
    }

    public function testDelimitadoresMultiples() {
        $this->assertEquals($this->sc->add("//[+][%]\n1+2%3"), 6);
        $this->assertEquals($this->sc->add("//[+**][%--]\n1+**2%--3"), 6);
    }

}

?>
