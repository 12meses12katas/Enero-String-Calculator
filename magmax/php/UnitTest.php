<?php

require_once 'PHPUnit/Framework.php';

require_once './string_calculator.php';

class StringCalculatorTest extends PHPUnit_Framework_TestCase {
  public function test_empty_string_returns_0() {
    $this->assertEquals(0, add(""));
  }

  public function test_string_with_one_number_returns_the_number() {
    $this->assertEquals(5, add("5"));
  }

  public function test_string_with_two_numbers_return_the_sum_of_them() {
    $this->assertEquals(7, add("4,3"));
  }

  public function test_string_with_three_numbers_returns_the_sum_of_them(){
    $this->assertEquals(17, add("9,2,6"));
  }

  public function test_new_line_works_as_separator() {
    $this->assertEquals(3, add("2\n1"));
  }

  public function test_can_combine_comas_and_new_lines() {
    $this->assertEquals(143, add("15\n73,12,10\n3\n30"));
  }

  public function test_can_change_separator() {
    $this->assertEquals(12, add("//;\n3;9"));
  }

  public function test_can_use_special_separators_too() {
    $this->assertEquals(15, add("//*\n5*10"));
  }

  public function test_use_numbers_as_separators() {
    $this->assertEquals(3, add("//9\n192"));
  }

  public function test_negatives_are_not_allowed() {
    $this->setExpectedException('InvalidArgumentException');
    add("-1");
  }

  public function test_negatives_are_not_allowed_and_are_returned() {
    try {
      add("-3");
    }
    catch (InvalidArgumentException $e) {
      $this->assertTrue(strpos($e->getMessage(), '-3') >=0 );
      return;
    }
    $this->fail('must not be here');
  }

  public function test_delimiters_can_be_any_length() {
    $this->assertEquals(22, add("//***\n10***9***3"));
  }

  public function test_multiple_delimiters() {
    $this->assertEquals(15, add("//[,][;]\n1,2;3,4;5"));
  }

  public function test_multiple_delimiters_with_enter() {
    $this->assertEquals(12, add("//[,][;]\n1,2;3\n4;2"));
  }

  public function test_multiple_delimiters_with_diferent_length() {
    $this->assertEquals(11, add("//[***][----]\n2***3----4\n2"));
  }

  public function test_big_numbers_are_ignored() {
    $this->assertEquals(20, add("2,1001,18"));
  }
}

class SpreadSeparatorsTest extends PHPUnit_Framework_TestCase {
  public function test_empty_string(){
    $this->assertEquals(array(''), spread_separators(""));
  }

  public function test_one_element(){
    $this->assertEquals(array('*'), spread_separators("*"));
  }

  public function test_one_element_between_blackets(){
    $this->assertEquals(array('*'), spread_separators("[*]"));
  }

  public function test_two_elements(){
    $this->assertEquals(array('*', ';'), spread_separators("[*][;]"));
  }

  public function test_three_elements() {
    $this->assertEquals(array('1','2','3'), spread_separators("[1][2][3]"));
  }

  public function test_spread_separators_works_as_spected() {
    $this->assertEquals(array(',', ';'), spread_separators("[,][;]"));
  }
}

class SeparateTest extends PHPUnit_Framework_TestCase {
  public function test_simple() {
    $this->assertEquals(array('1', '2'), separate('1,2', array(',')));
  }

  public function test_complex() {
    $this->assertEquals(array('1', '2', '3', '4', '2'), separate("1,2;3\n4;2", array(',', ';')));
  }
}

class SeparatorsToRegexpTests extends PHPUnit_Framework_TestCase {
  public function test_two_separatos() {
    $this->assertEquals("/(\n|,|;)/", separators_to_regexp(array(',', ';')));
  }
}

?>