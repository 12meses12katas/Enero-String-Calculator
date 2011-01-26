<?php

require_once '../StringCalculator.php';

/**
 * Screencast: http://vimeo.com/19208035
 */
class StringCalculatorTest extends PHPUnit_Framework_TestCase {

  protected $strinCalculator;

  public function setUp() {
    $this->strinCalculator = new StringCalculator;
  }

  public function getSumTestCases() {
    return array(
        'Empty string should return zero' => array('str' => "", 'expected' => 0),
        'One number string should return the number' => array('str' => "1", 'expected' => 1),
        'Two number string should return the sum' => array('str' => "1,2", 'expected' => 3),
        'Three number string should return the sum' => array('str' => "1,2,3", 'expected' => 6),
        'New line as separator is accepted and can be mixed with comma' => array('str' => "1\n2,3", 'expected' => 6),
        'Custom one-char separators can be defined' => array('str' => "//;\n1;2", 'expected' => 3),
        'Numbers bigger than 1000 should be ignored' => array('str' => "1,1001", 'expected' => 1),
        'Custom many-char separators can be defined' => array('str' => "//[***]\n1***2***3", 'expected' => 6),
        'Many custom separators can be defined' => array('str' => "//[*][%]\n1*2%3", 'expected' => 6),
    );
  }

  public function testFramework() {
    $this->assertTrue(true);
  }

  /**
   * @dataProvider getSumTestCases
   */
  public function testSumCases($str, $expected) {
    $this->assertEquals($expected, $this->strinCalculator->add($str));
  }

  public function testNegativeNumbersAreNotAllowed() {
    $this->setExpectedException('Exception');
    $this->strinCalculator->add("1,-2");
  }

  public function testOneNumberStringShouldReturnTheNumber() {
    $stringCalculator = new StringCalculator();
    $actual = $stringCalculator->add("1");
    $this->assertEquals(1, $actual);
  }

}