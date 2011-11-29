<?php
class StringCalculator {
  public function add($string) {
    if ($string == "")
      return 0;

    $this->string = $string;
    return $this->perform_sum();
  }

  private function perform_sum(){
    $validator = new NumberValidator();
    $result = 0;
    foreach($this->divide_string() as $each)
      $result += $validator->normalize(intval($each));
    $validator->assert_valid_numbers();
    return $result;
  }

  private function divide_string() {
    $regexp = $this->get_separator_regexp();
    return preg_split($regexp, $this->string);
  }

  private function get_separator_regexp() {
    $regexpbuilder = new RegexpBuilder();
    $finder = new SeparatorFinder();
    return $regexpbuilder->arrayToRegexp($finder->get_separators($this->string));
  }
}

class NumberValidator{
  private $negatives = array();

  public function normalize($value) {
    return $this->validate($value) ? $value : 0;
  }

  private function validate($number) {
    if ($number < 0) {
      $this->negatives[] = $number;
      return false;
    }

    return $number < 1000;
  }

  public function assert_valid_numbers() {
    if (count($this->negatives) > 0)
      throw new InvalidArgumentException(join(',',$this->negatives));
  }
}

class RegexpBuilder{
  public function arrayToRegexp($vector) {
    return "/(\n|". join('|', $this->normalize($vector)) . ")/";
  }

  public function normalize($vector){
    $result = array();
    foreach($vector as $each) {
      $result[] = quotemeta($each);
    }
    return $result;
  }
}

class SeparatorFinder{
  public function get_separators($string) {
    if (preg_match("*^//(.+)\n*", $string, $matchers))
      return array_merge(array('\n'), $this->resolve($matchers[1]));
    return array('\n', ',');
  }

  private function resolve ($string) {
    if (preg_match_all("*\[([^\]]+)\]*", $string, $matchers))
      return $matchers[1];
    return array($string);
  }
}

?>
