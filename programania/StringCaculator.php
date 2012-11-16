<?php

class StringCalculator
{
    const STANDARD_SEPARATOR = ',';

    function add($operation)
    {
        if ($operation == "")
            return 0;

        list($header, $searches) = $this->headerAndSeparators($operation);

        $numberString = str_replace($header, '', $operation);

        $normalized = str_replace($searches, self::STANDARD_SEPARATOR, $numberString);

        $numberArray = explode(self::STANDARD_SEPARATOR, $normalized);

        $this->validateNoNegatives($numberArray);

        $numberArray = $this->ignoreGreatherThanOneThousand($numberArray);

        return array_sum($numberArray);
    }

    function ignoreGreatherThanOneThousand($numeros)
    {
        $numeros = array_filter($numeros, function ($value) {

                            return $value < 1000;
                        });

        return $numeros;
    }

    function validateNoNegatives($numeros)
    {

        $negativos = array_filter($numeros, function ($value) {

                            return $value < 0;
                        });

        if (count($negativos) > 0)
            throw new Exception("negativos: " . implode(',', $negativos));
    }

    function headerAndSeparators($sumandos)
    {
        $reemplazables = array('\n');

        $clean = str_replace('//', '', $sumandos);

        if ($clean != $sumandos) {

            list($separator, $clean) = explode('\n', $clean);

            $reemplazables[] = $separator;

            return array("//$separator\n", $reemplazables);
        }

        return array("", $reemplazables);
    }

}