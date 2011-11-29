<?php

function add($cadena) {
  if ($cadena == "")
    return 0;

  $separators = get_separators($cadena);
  $vector = separate($cadena, $separators);

  assertNoNegatives($vector);

  return sum($vector);
}

function assertNoNegatives($vector) {
  $negatives = array();
  foreach($vector as $value) {
    if (intval($value) < 0)
      $negatives[] = $value;
  }
  if (count($negatives) > 0)
    throw new InvalidArgumentException(arrayToString($negatives));
}

function arrayToString($array) {
  return join(',', $array);
}

function get_separators($cadena) {
  if (preg_match('*^//(.+)\n*', $cadena, $matches))
    return spread_separators($matches[1]);
  return array(',');
}

function spread_separators($cadena) {
  if (preg_match_all('/\[([^\]]+)\]/', $cadena, $matches))
    return $matches[1];
  return array($cadena);
}

function separate($cadena, $separators) {
  $regexp = separators_to_regexp($separators);
  return preg_split($regexp, $cadena);
}

function separators_to_regexp($separators) {
  $normalized = quote_all($separators);
  $serialized = join('|', $normalized);

  return "/(\n|". $serialized . ")/";
}

function quote_all($vector) {
  $result = array();
  foreach($vector as $each) {
    $result[] = quotemeta($each);
  }
  return $result;
}

function sum($vector) {
  $result = 0;
  foreach($vector as $value) {
    $result += normalize_value($value);
  }
  return $result;
}

function normalize_value($string) {
  $int = intval($string);
  if ($int > 1000)
    return 0;
  return $int;
}

?>
