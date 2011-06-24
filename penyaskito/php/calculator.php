<?php

class calculator {
  public function add($params) {
    $args = preg_split('[,|\n]', $params);
    $result = 0;
    foreach ($args as $arg) {
      $result += (int)$arg;
    }
    return $result;
  }
}

?>



