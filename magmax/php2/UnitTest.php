<?php

require_once 'PHPUnit/Framework.php';

require_once './string_calculator.php';

class StringCalculatorTest extends PHPUnit_Framework_TestCase {

  private $sut = Null;

  function setUp() {
    $this->sut = new StringCalculator();
  }

  function test_creation() {
    $this->assertNotNull($this->sut);
  }

  function test_add_empty_string_is_zero() {
    $string = "";
    $expected = 0;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_add_only_a_number() {
    $string = "1";
    $expected = 1;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_add_only_a_number_again() {
    $string = "3";
    $expected = 3;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_add_two_numbers() {
    $string = "3,4";
    $expected = 7;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_add_three_numbers() {
    $string = "3,4,5";
    $expected = 12;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_numbers_can_be_separated_by_linefeed() {
    $string = "3,4\n1";
    $expected = 8;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_separator_can_be_changed() {
    $string = "//;\n1;8";
    $expected = 9;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_separator_can_be_changed_and_linefeed_works() {
    $string = "//;\n1\n8;9";
    $expected = 18;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_numbers_over_1000_are_ignored(){
    $string = "//;\n1\n8;1001";
    $expected = 9;

    $this->assertEquals($expected, $this->sut->add($string));
  }

  function test_negatives_gives_an_exception() {
    $this->setExpectedException('InvalidArgumentException');
    $this->sut->add("-1");
  }

  public function test_negatives_are_not_allowed_and_are_returned() {
    try {
      $this->sut->add("-3");
    }
    catch (InvalidArgumentException $e) {
      $this->assertTrue(strpos($e->getMessage(), '-3') >=0 );
      return;
    }
    $this->fail('must not be here');
  }

  public function _test_delimiters_can_be_any_length() {
    $this->assertEquals(22, $this->sut->add("//***\n10***9***3"));
  }

}

class NumberValidatorTest extends PHPUnit_Framework_TestCase {
  function setUp() {
    $this->sut = new NumberValidator();
  }

  function test_normal_number() {
    $this->assertEquals(14, $this->sut->normalize(14));
  }

  function test_big_number() {
    $this->assertEquals(0, $this->sut->normalize(1001));
  }

  function test_negative_number() {
    $this->assertEquals(0, $this->sut->normalize(1001));
  }

  function test_validation_without_negatives() {
    $this->sut->normalize(1001);
    $this->sut->assert_valid_numbers();
  }

  function test_validation_with_negatives() {
    $this->setExpectedException('InvalidArgumentException');
    $this->sut->normalize(-1);
    $this->sut->assert_valid_numbers();
  }
}

class SeparatorFinderTester extends PHPUnit_Framework_TestCase {
  function setUp() {
    $this->sut = new SeparatorFinder();
  }

  function test_basic_separator(){
    $this->assertEquals(array('\n', ','), $this->sut->get_separators('1,2,3'));
  }

  function test_change_of_separator(){
    $this->assertEquals(array('\n', ';'), $this->sut->get_separators("//;\n1;2"));
  }

  function test_long_separators(){
    $this->assertEquals(array('\n', '***'), $this->sut->get_separators("//***\n1***2"));
  }

  function test_brackets_are_ignored() {
    $this->assertEquals(array('\n', '*'), $this->sut->get_separators("//[*]\n1*2"));
  }

  function test_more_than_one_separator() {
    $this->assertEquals(array('\n', ',', ';'), $this->sut->get_separators("//[,][;]\n1"));
  }

  function test_more_than_one_long_separator() {
    $this->assertEquals(array('\n', ',,', ';;'), $this->sut->get_separators("//[,,][;;]\n1"));
  }
}

?>