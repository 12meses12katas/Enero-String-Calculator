<?php

namespace SeparatorExtractor;

require_once 'Base.php';

class Regexp implements Base {
  const CUSTOM_SEPARATOR_REGEXP = "/\[(.+?)\]/";

  public function extract($separatorDefinition) {
    $matches = array();
    preg_match_all(self::CUSTOM_SEPARATOR_REGEXP, $separatorDefinition, $matches);
    return $matches[1];
  }

}