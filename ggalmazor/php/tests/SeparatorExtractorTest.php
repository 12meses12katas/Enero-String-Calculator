<?php

require_once '../SeparatorExtractor/Regexp.php';
require_once '../SeparatorExtractor/Tokenizer.php';

class SeparatorExtractorTest extends \PHPUnit_Framework_TestCase {

  protected $testCase = "[***][%%%]";
  protected $expected = array('***', '%%%');

  public function getSeparatorExtractors() {
    return array(
        'Regular Expression separator extractor' => array('separatorExtractor' => new SeparatorExtractor\Regexp),
        'Tokenizer separator extractor' => array('separatorExtractor' => new SeparatorExtractor\Tokenizer)
    );
  }

  /**
   * @dataProvider getSeparatorExtractors
   */
  public function testSeparatorExtractors($separatorExtractor) {
    $this->assertEquals($this->expected, $separatorExtractor->extract($this->testCase));
  }

}