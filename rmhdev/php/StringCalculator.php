<?php
class StringCalculator {

    const DEFAULT_DELIMITER = ',';
    const REGEX_DELIMITER = '/\[(.*?)\]/';

    protected $string;
    protected $delimiters;
    protected $isSimpleDelimiter;

    public static function sum($string){
        $stringCalculatorObject = new self($string);
        return $stringCalculatorObject->addition();
    }

    public function __construct($string){
        $this->string = $string;
        $this->isSimpleDelimiter = true;
        $this->delimiters = $this->calculateDelimiters();
    }
    public function addition(){
        $sum = 0;
        foreach ($this->calculateArrayOfNumbers() as $number){
            $sum += (int)$number;
        }
        return $sum;
    }
    
    public function getString(){
        return $this->string;
    }

    protected function calculateDelimiters(){
        if ($this->stringHasDelimiterCode()){
            return $this->calculateDelimitersFromDelimiterCodeInString();
        }
        return array(self::DEFAULT_DELIMITER);
    }

    protected function calculateDelimitersFromDelimiterCodeInString(){
        $string = $this->getString();
        if (preg_match_all(self::REGEX_DELIMITER, $string, $matches)){
            $this->isSimpleDelimiter = false;
            return $matches[1];
        }
        return array($string[2]);
    }

    protected function isSimpleDelimiter(){
        return $this->isSimpleDelimiter;
    }

    protected function getDelimiters(){
        return $this->delimiters;
    }

    protected function getFinalDelimiter(){
        return $this->delimiters[0];
    }

    protected function calculateArrayOfNumbers(){
        $numbers = $this->explodeStringInArrayOfIntegers();
        $this->validateArrayOfNumbers($numbers);
        return $numbers;
    }

    protected function explodeStringInArrayOfIntegers(){
        $string = $this->extractStringOnlyWithNumbersAndFinalDelimiter();
        return $this->getCorrectValuesForNumbers(explode($this->getFinalDelimiter(), $string));
    }

    protected function extractStringOnlyWithNumbersAndFinalDelimiter(){
        $stringAfterDelimiterCode = $this->getStringAfterDelimiterCode();
        $onlyNumbersAndFinalDelimiter = str_replace(
            $this->getDelimiters(), $this->getFinalDelimiter(), $stringAfterDelimiterCode
        );
        return $this->replaceNewLinesWithFinalDelimiter($onlyNumbersAndFinalDelimiter);
    }

    protected function replaceNewLinesWithFinalDelimiter($string){
        return str_replace("\n", $this->getFinalDelimiter(), $string);
    }
    
    protected function getStringAfterDelimiterCode(){
        return $this->stringHasDelimiterCode() ? 
            substr($this->getString(), $this->lengthOfDelimiterCodeInString()) :
            $this->getString();
    }

    protected function lengthOfDelimiterCodeInString(){
        $lengthToIgnore = 5;
        if ($this->stringHasDelimiterCode()){
            $lengthOfDelimiters = 0;
            foreach ($this->getDelimiters() as $delimiter){
                $lengthOfDelimiters += strlen($delimiter) + ($this->isSimpleDelimiter() ? 0 : 2);
            }
            $lengthToIgnore = 2 + $lengthOfDelimiters + 2;
        }
        return $lengthToIgnore;
    }


    protected function validateArrayOfNumbers($numbers = array()){
        $negatives = array();
        foreach ($numbers as $number){
            if ($number < 0){
                $negatives[] = $number;
            }
        }
        if ($negatives){
            throw new NegativesNotAllowed(implode(",", $negatives));
        }
        return true;
    }

    protected function stringHasDelimiterCode(){
        if (strlen($this->getString()) > 3){
            if (substr($this->getString(), 0, 2) == "//"){
                return true;
            }
        }
        return false;
    }
    protected function getCorrectValuesForNumbers($numbers = array()){
        for ($i=0; $i<sizeof($numbers); $i++){
            $numbers[$i] = $this->getCorrectValueForNumber($numbers[$i]);
        }
        return $numbers;
    }

    protected function getCorrectValueForNumber($number){
        $number = (int)$number;
        return ($number <= 1000) ? $number : 0;
    }

}


class NegativesNotAllowed extends Exception {
    
}