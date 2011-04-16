<?php

require_once 'StringCalculator.php';
require_once 'SeparatorExtractor/Regexp.php';
require_once 'SeparatorExtractor/Tokenizer.php';

class StringCalculatorFactory {
  const EXTRACTOR_REGEXP = 'regexp';
  const EXTRACTOR_TOKENIZER = 'tokenizer';

  public static function create($extractorType = self::EXTRACTOR_REGEXP) {
    switch ($extractorType) {
      case self::EXTRACTOR_REGEXP:
        $extractor = new SeparatorExtractor\Regexp;
        break;
      case self::EXTRACTOR_TOKENIZER:
        $extractor = new SeparatorExtractor\Tokenizer;
        break;
    }
    return new StringCalculator($extractor);
  }

}