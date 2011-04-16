<?php
require_once('simpletest/autorun.php');
require_once('calculator.php');

class TestCalculator extends UnitTestCase {
  public function testAddWithTwoArgs() {
    $calc = new Calculator();
    $expected = 3;
    $value = $calc->add("1,2");
    $this->assertEqual($expected, $value);
  }
  public function testAddWithOneArg() {
    $calc = new Calculator();
    $expected = 1;
    $value = $calc->add("1");
    $this->assertEqual($expected, $value);
  }
  public function testAddWithNoArgs() {
    $calc = new Calculator();
    $expected = 0;
    $value = $calc->add("");
    $this->assertEqual($expected, $value);
  }
  public function testAddWithMultipleArgs() {
    $calc = new Calculator();
    $expected = 6;
    $value = $calc->add("1,2,3");
    $this->assertEqual($expected, $value);
  }
  public function testAddWithMultipleArgsAndLineBreaks() {
    $calc = new Calculator();
    $expected = 6;
    $value = $calc->add("1\n2,3");
    $this->assertEqual($expected, $value);
  }








}


?>
