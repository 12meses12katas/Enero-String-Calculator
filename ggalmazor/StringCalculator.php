<?php

class StringCalculator {
  const SEPARATOR_NEW_LINE = "\n";
  const SEPARATOR_COMMA = ",";

  public function add($str) {
    if ($str == "") {
      return 0;
    }
    if ($this->hasCustomSeparator($str)) {
      $str = $this->processCustomSeparators($str);
    }
    $forbiddenNumbers = array();
    $validNumbers = array();
    foreach ($this->extractNumbers($str) as $number) {
      if ($this->isNumberForbidden($number)) {
        $forbiddenNumbers[] = $number;
      }
      if ($this->isNumberValid($number)) {
        $validNumbers[] = $number;
      }
    }
    if (count($forbiddenNumbers)) {
      throw new Exception(sprintf("Found some negative numbers in the string: %s", implode(", ", $forbiddenNumbers)));
    }
    return array_sum($validNumbers);
  }

  protected function isNumberForbidden($number) {
    return $number < 0;
  }

  protected function isNumberValid($number) {
    return $number < 1000;
  }

  protected function hasCustomSeparator($str) {
    return substr($str, 0, 2) == "//";
  }

  protected function unifySeparators($str) {
    return str_replace(self::SEPARATOR_NEW_LINE, self::SEPARATOR_COMMA, $str);
  }

  protected function extractNumbers($str) {
    $str = $this->unifySeparators($str);
    return explode(self::SEPARATOR_COMMA, $str);
  }

  protected function processCustomSeparators($str) {
    $header = $this->extractHeader($str);
    $str = $this->removeHeader($str);
    $separators = $this->extractSeparators($header);
    return $this->normalizeSeparators($separators, $str);
  }

  protected function extractHeader($str) {
    return substr($str, 0, strpos($str, "\n"));
  }

  protected function removeHeader($str) {
    return substr($str, strpos($str, "\n") + 1, strlen($str));
  }

  protected function extractSeparators($header) {
    if ($header[2] == "[") {
      $matches = array();
      $header = str_replace("//", "", $header);
      preg_match_all("/\[(.+?)\]/i", $header, $matches);
      return $matches[1];
    }
    return array($header[2]);
  }

  protected function normalizeSeparators(array $separators, $str) {
    return str_replace($separators, self::SEPARATOR_COMMA, $str);
  }

}