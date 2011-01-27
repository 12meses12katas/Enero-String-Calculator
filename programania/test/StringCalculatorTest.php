<?php

require_once 'PHPUnit/Autoload.php';
require_once dirname(__FILE__) . '/../StringCalculator.php';

/**
 * String Calculator

  1. Create a simple String calculator with a method int Add(string numbers)
  1. The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
  2. Start with the simplest test case of an empty string and move to 1 and two numbers
  3. Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
  4. Remember to refactor after each passing test
  2. Allow the Add method to handle an unknown amount of numbers
  3. Allow the Add method to handle new lines between numbers (instead of commas).
  1. the following input is ok:  “1\n2,3”  (will equal 6)
  2. the following input is NOT ok:  “1,\n”
  3. Make sure you only test for correct inputs. there is no need to test for invalid inputs for these katas
  4. Allow the Add method to handle a different delimiter:
  1. to change a delimiter, the beginning of the string will contain a separate line that looks like this:   “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
  2. the first line is optional. all existing scenarios should still be supported
  5. Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was passed.if there are multiple negatives, show all of them in the exception message stop here if you are a beginner. Continue if you can finish the steps so far in less than 30 minutes.
  6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001  = 2
  7. Delimiters can be of any length with the following format:  “//[delimiter]\n” for example: “//[***]\n1***2***3” should return 6
  8. Allow multiple delimiters like this:  “//[delim1][delim2]\n” for example “//[*][%]\n1*2%3” should return 6.
  9. make sure you can also handle multiple delimiters with length longer than one char

 */
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
            , " “//[***]\n1***2***3” should return 6" => array("//[***]\n1***2***3", 6)
            , "//[*][%]\n1*2%3” should return 6" => array("//[*][%]\n1*2%3", 6)
        );
    }

    /**
     * @dataProvider provider
     */
    public function __testStringCalculator($cadena, $expected)
    {

        $calculator = new StringCalculator();

        $this->assertEquals($expected, $calculator->add($cadena));
    }

    public function __testStringCalculatorException()
    {
        $this->setExpectedException("Exception");

        $calculator = new StringCalculator();

        $calculator->add("-3,1");
    }

    public function test_StrTok(){

        $str = "//[coco][p][tralari] 3tralari5coco3tralari";

        $actual = array();

       
        $actual[] = strtok($str, '[]');

        while($actual[] = strtok('[]')){

        }

        $expected = array();

        $expected[0] = '//';
        $expected[1] = 'coco';
        $expected[2] = 'p';
        $expected[3] = 'tralari';
        $expected[4] = ' 3tralari5coco3tralari';
        $expedted[5] = '';

        $this->assertEquals($expected, $actual);


    }

     public function test_StrTok_Dos(){

        $str = "//;;;\n 3;;;4";
        $token = '//\n';

        $actual = array();

        $actual = array(strtok($str, $token));

        while($actual[] = strtok($token)){

        }

        $expected = array(';;;','3;;;4','');
        $this->assertEquals($expected, $actual);

     }

}