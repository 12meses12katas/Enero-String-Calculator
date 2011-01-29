<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of StringCalculator
 *
 * @author ruben
 */
class StringCalculator {

    //put your code here
    const PATRON_SUM =  '/([[:digit:]]+(,))*([[:digit:]]+)$/';
    const PATRON_ERR_NEG  =  '/(-\d+)/';
    const PATRON_HAS_DELIMITER = '/^\/\/(.*)\n/';
    const PATRON_DELIMITER_SHORT = '/^\/\/(\W+)\n/';
    const PATRON_DELIMITER_LONG = '/^\/\/\[(\W+)\]\n/';
    const PATRON_DELIMITER_MULTI = '/^\/\/(\[(\W+)\])(\[(\W+)\])\n/';

    const PATRON_NEWLINE = '/[\r\n]/';
    const PATRON_FORBIDDEN_DELIMITERS = '/(\*|\+)/';
    const DEFAULT_DELIMITER = ',';

    protected  $delimiter = ',';


    private function hasDelimiter($string) {
        return preg_match(self::PATRON_HAS_DELIMITER,$string,$match);
    }

    private function configureDelimiter($string) {

        $patron = '';

        if (preg_match(self::PATRON_FORBIDDEN_DELIMITERS,$string,$match)) $string = preg_replace(self::PATRON_FORBIDDEN_DELIMITERS,self::DEFAULT_DELIMITER,$string);
        
        if (preg_match(self::PATRON_DELIMITER_MULTI,$string,$match)) {

            $patron = self::PATRON_DELIMITER_MULTI;
            $patronUnifiedDelimiter = "/(".$match[2]."|".$match[4].")/";
            $string = preg_replace($patronUnifiedDelimiter,self::DEFAULT_DELIMITER,$string);
            $this->delimiter = self::DEFAULT_DELIMITER;

        } else {

            if (preg_match(self::PATRON_DELIMITER_SHORT,$string,$match)) $patron = self::PATRON_DELIMITER_SHORT;
            if (preg_match(self::PATRON_DELIMITER_LONG,$string,$match)) $patron = self::PATRON_DELIMITER_LONG;
            $this->delimiter = $match[1];
        }

        $string =  preg_replace($patron,"",$string);
        
        return $string;
    }

    private function cleanString($string) {

        if (preg_match(self::PATRON_ERR_NEG,$string,$values)) throw new Exception("negatives not allowed");

        $stringClean = preg_replace(self::PATRON_NEWLINE,$this->delimiter,$string);

        if (preg_match(self::PATRON_SUM, $stringClean, $match)) return preg_split("/".$this->delimiter."/", $stringClean);
        else throw new Exception ('Unknown format');
    }


    public function add($string) {

        $total = 0;

        if ($string == '') return 0;
        
        if ($this->hasDelimiter($string)) $string = $this->configureDelimiter($string);

        $arrayOfNumbers = $this->cleanString($string);

        foreach ($arrayOfNumbers as $i => $number) {
                if ($number < 1000) $total += $number;
        }
        return $total;

    }

}

?>
