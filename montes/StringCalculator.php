<?php

class StringCalculator
{
    public function add($numbers)
    {
        $numbers = $this->extractDelimiters($numbers);
        
        if ($numbers == '') {
            return 0;
        }
        elseif (is_numeric($numbers)) {
            return $numbers;
        }
        elseif (preg_match('/^(-?\d+)([\\n\D]-?\d+)+$/m', $numbers)) {
            preg_match_all('/(-?\d)+/m', $numbers, $matches);
            $total = 0;
            foreach ($matches[0] as $number) {
                if ($number < 0) {
                    $this->throwNegativesException($matches[0]);
                }
                elseif ($number < 1001) {
                    $total += $number;
                }
            }
            return $total;
        }
    }

    protected function extractDelimiters($numbers)
    {
        if (preg_match('%^//([^\n])\n(.*)$%ms', $numbers, $matches)) {
            $numbers = $matches[2];
        }
        elseif (preg_match('%^//\[(?P<delimiter>[^\]]+)\]\n(.*)$%ms', $numbers, $matches)) {
            $delimiter = $matches['delimiter'];
            $numbers = $matches[2];
            $numbers = str_replace($delimiter, ',', $numbers);
        }
        elseif (preg_match('%^//\[(?P<delimiter1>[^\]]+)\]\[(?P<delimiter2>[^\]]+)\]\n(.*)$%ms', $numbers, $matches)) {
            $delimiter1 = $matches['delimiter1'];
            $delimiter2 = $matches['delimiter2'];
            $numbers = $matches[3];
            $numbers = str_replace($delimiter1, ',', $numbers);
            $numbers = str_replace($delimiter2, ',', $numbers);
        }

        return $numbers;
    }

    protected function throwNegativesException($numbers)
    {
        $negativeNumbers = '';
        foreach ($numbers as $number) {
            if ($number < 0) {
                $negativeNumbers .= $number . ' ';
            }
        }
        throw new Exception(
            'Negatives not allowed: '.trim($negativeNumbers));
    }
}


?>
