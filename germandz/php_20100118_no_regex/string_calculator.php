<?php

define (UPPER_LIMIT, 1000);
define (STANDARD_SEPARATOR, ',');
define (NEWLINE_SEPARATOR, "\n");
define (MARK_FOR_CUSTOM_SEPARATOR, '//');
define (START_SEPARATOR, '[');
define (END_SEPARATOR, ']');

class StringCalculator {
    
    public function add($string) {
        $numbers = $this->obtain_numbers($string);
        $this->reject_negatives($numbers);
        return $this->add_numbers($numbers);
    }
    
    private function obtain_numbers($string) {
        list ($separators, $numbers_part) = $this->parse_string($string);
        $normalized_string = $this->normalize($separators, $numbers_part);
        return explode(STANDARD_SEPARATOR, $normalized_string);
    }
    
    private function parse_string($string) {
        $separators = $this->find_valid_separators($string);
        $numbers_part = $this->find_numbers_part($string);
        return array($separators, $numbers_part);
    }
    
    private function first_line_with_separator($string) {
        return strpos($string, MARK_FOR_CUSTOM_SEPARATOR) === 0;
    }
    
    private function find_valid_separators($string) {
        $separators = array(NEWLINE_SEPARATOR);
        if ($this->first_line_with_separator($string)) {
            $first_line = substr($string, 0, strpos($string, "\n"));
            $separators_info = substr($first_line, 2);
            $separators = $this->extract_separators($separators_info);
        }
        return $separators;
    }
    
    private function extract_separators($separators_info) {
        $separators = array($separators_info);
        if ($this->multiple_separators($separators_info)) {
            $separators = explode(END_SEPARATOR . START_SEPARATOR, substr($separators_info, 1, -1));
        }
        return $separators;
    }
    
    private function multiple_separators($separators_info) {
        return (substr($separators_info, 0, 1) == START_SEPARATOR) && (substr($separators_info, -1) == END_SEPARATOR);
    }
    
    private function find_numbers_part($string) {
        $numbers_part = $string;
        if ($this->first_line_with_separator($string)) {
            $numbers_part = substr($string, strpos($string, NEWLINE_SEPARATOR) + 1);
        }
        return $numbers_part;
    }
        
    private function normalize($separators, $string) {
        return str_replace($separators, STANDARD_SEPARATOR, $string);
    }
    
    private function reject_negatives($numbers) {
        $negatives = array();
        foreach($numbers as $number) {
            if ($number < 0) {
                $negatives[] = $number;
            }
        }
        if (!empty($negatives)) {
            throw new InvalidArgumentException("negatives not allowed: " . implode(',', $negatives));
        }
    }
    
    private function add_numbers($numbers) {
        $sum = 0;
        foreach($numbers as $number) {
            if ($number < UPPER_LIMIT) {
                $sum += $number;
            }
        }
        return $sum;
    }
}
