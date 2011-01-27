<?php

namespace SeparatorExtractor;

require_once 'Base.php';

class Tokenizer implements Base {
  const TOKENS = "[]";

  public function extract($separatorDefinition) {
    $separators = array();
    $separators[] = strtok($separatorDefinition, self::TOKENS);
    while ($token = strtok(self::TOKENS)) {
      $token = trim($token);
      if ($token != "") {
        $separators[] = $token;
      }
    }
    return $separators;
  }

}