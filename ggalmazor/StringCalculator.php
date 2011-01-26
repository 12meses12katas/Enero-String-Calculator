<?php

require_once 'SeparatorExtractor/Base.php';

/**
 * Screencast de la kata básica (sin DI) en http://vimeo.com/19208035
 */
class StringCalculator {
  const SEPARATOR_NEW_LINE = "\n";
  const SEPARATOR_COMMA = ",";
  const CUSTOM_SEPARATOR_START = "//";
  const CUSTOM_SEPARATOR_END = "\n";
  const CUSTOM_SEPARATOR_REGEXP = "/\[(.+?)\]/";
  const VALID_NUMBER_BOTTOM = 0;
  const VALID_NUMBER_CEIL = 1000;

  protected $separatorExtractor;

  public function __construct(SeparatorExtractor\Base $separatorExtractor) {
    $this->separatorExtractor = $separatorExtractor;
  }

  public function add($str) {
    if ($str == "")
      return 0;
    if ($this->hasCustomSeparator($str))
      $str = $this->processCustomSeparators($str);
    $forbiddenNumbers = $validNumbers = array();
    foreach ($this->extractNumbers($str) as $number) {
      if ($this->isNumberForbidden($number))
        $forbiddenNumbers[] = $number;
      if ($this->isNumberValid($number))
        $validNumbers[] = $number;
    }
    if (count($forbiddenNumbers))
      throw new Exception(sprintf("Found some negative numbers in the string: %s", implode(", ", $forbiddenNumbers)));
    return array_sum($validNumbers);
  }

  protected function isNumberForbidden($number) {
    return $number < self::VALID_NUMBER_BOTTOM;
  }

  protected function isNumberValid($number) {
    return $number < self::VALID_NUMBER_CEIL;
  }

  protected function hasCustomSeparator($str) {
    return substr($str, 0, 2) == self::CUSTOM_SEPARATOR_START;
  }

  protected function unifySeparators($str) {
    return str_replace(self::SEPARATOR_NEW_LINE, self::SEPARATOR_COMMA, $str);
  }

  protected function extractNumbers($str) {
    $str = $this->unifySeparators($str);
    return explode(self::SEPARATOR_COMMA, $str);
  }

  protected function processCustomSeparators($str) {
    list($header, $str) = $this->separateHeader($str);
    $separators = $this->extractSeparators($header);
    return $this->normalizeSeparators($separators, $str);
  }

  protected function separateHeader($str) {
    return array($this->extractHeader($str), $this->removeHeader($str));
  }

  protected function extractHeader($str) {
    return substr($str, 0, strpos($str, self::CUSTOM_SEPARATOR_END));
  }

  protected function removeHeader($str) {
    return substr($str, strpos($str, self::CUSTOM_SEPARATOR_END) + 1, strlen($str));
  }

  protected function extractSeparators($header) {
    $header = $this->cleanHeader($header);
    if ($this->isComplexSeparatorDefinition($header)) {
      return $this->separatorExtractor->extract($header);
    }
    return array($header[0]);
  }

  protected function cleanHeader($header) {
    return str_replace(self::CUSTOM_SEPARATOR_START, "", $header);
  }

  protected function isComplexSeparatorDefinition($header) {
    return $header[0] == "[";
  }

  protected function normalizeSeparators(array $separators, $str) {
    return str_replace($separators, self::SEPARATOR_COMMA, $str);
  }

}