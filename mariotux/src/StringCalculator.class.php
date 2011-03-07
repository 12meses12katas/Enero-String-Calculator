<?php
class StringCalculator{

    private $parser;
    private $validator;

    public function __construct($parser,$validator){
        $this->parser = $parser;
        $this->validator = $validator;
    }

    public function add($str_input){
        return array_sum($this->validator->validateInput($this->parser->parseNumbersSequence($str_input)));
    }
}
?>
