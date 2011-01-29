<?php

class Parser {
    public function stringNumbers($str_input) {
        return $this->getArrayNumbers($str_input);
    }
    
    private function getArrayNumbers($str){
        $delimiters = array_unique(preg_split("/-?\d/", $str, null, PREG_SPLIT_NO_EMPTY));
        foreach($delimiters as $delimiter){
            $str = str_replace($delimiter,',',$str);
        }
        return preg_split("/,/", $str, null, PREG_SPLIT_NO_EMPTY);
    }
}

?>
