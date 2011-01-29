<?php
/**
 * My implementation of the kata StringCalculator with PHP and
 * without regular expresion.
 *
 * @author  Gonzalo Ayuso <gonzalo123@gmail.com>
 */

class StringCalculator
{
    const LIMITUP = 1000;
    const DEFAULT_DELIMITER = ",";

    /**
     * Main function. 
     * 
     * @param string $string 
     * @return integer
     */
    public function add($string)
    {
        if ($string == "") {
            return 0;
        }

        $string = $this->decodeString($string);
        $numbers = explode(self::DEFAULT_DELIMITER, $string);

        if (count($numbers) == 1) {
            if ($string < 0) {
                throw new InvalidArgumentException("negatives not allowed");
            } else {
                return $string; 
            }
        } 

        $errs = array();
        $out = 0;
        foreach ($numbers as $number) {
            if ($number < 0) {
                $errs[] = $number;
            } else {
                if ($number <= self::LIMITUP) {
                    $out+= $number;
                }
            }
        }

        if (count($errs) > 0) {
            throw new InvalidArgumentException("negatives not allowed. " . implode(', ', $errs));
        }
        return (integer) $out;
    }

    /**
     * This should be private but as I'm a newbie with TDD I make it public to test it 
     * 
     * @param string $string 
     * @return string
     */
    public function decodeString($string)
    {
        $delimiter = self::DEFAULT_DELIMITER;
        $multipleDelimiters = null;
        $string = str_replace("\n", self::DEFAULT_DELIMITER, $string);

        if (substr($string, 0, 3) == '//[' ) { 
            $delimiter = substr($string, 3, strpos($string, "]" . self::DEFAULT_DELIMITER) - 3);
            $string = substr($string, strpos($string, "]" . self::DEFAULT_DELIMITER) + 2);
            if (strpos($delimiter, "][") !== false) {
                // custom delimiter multibyte (multiple)
                $multipleDelimiters = explode("][", $delimiter);
                $delimiter = "[{$delimiter}]";
            } 
        } elseif (substr($string, 0, 2) == '//') {
            // custom delimiter (simple)
            $delimiter = $string[2];
            $string = substr($string, 4);
        }

        if (is_null($multipleDelimiters)) {
            $string = str_replace($delimiter, self::DEFAULT_DELIMITER, $string);
        } else {
            foreach ($multipleDelimiters as $_delimiter) {
                $string = str_replace($_delimiter, self::DEFAULT_DELIMITER, $string);
            }
        }
        return $string;
    }
}
