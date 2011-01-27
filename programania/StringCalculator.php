<?php

class StringCalculator
{
    const STANDARD_SEPARATOR = ',';

    function add($command)
    {
        if ($command == "")
            return 0;

        list($header, $searches) = $this->headerAndSeparators($command);

        $numberString = str_replace($header, '', $command);

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

    function headerAndSeparators($command)
    {
        $hookSeparatorType = strpos($command, '[');

        if ($hookSeparatorType)
            return $this->headerAndHookSeparators($command);

        $customSeparator = substr($command, 0, 2) == '//';

        if ($customSeparator)
            return $this->headerAndCustomSeparators($command);

        return array("", array('\n'));
    }

    function headerAndHookSeparators($command)
    {
        $clean = str_replace(array('//'), '', $command);

        $separators = array('\n');

        $separators[] = strtok($clean, '[]');

        while ($separators[] = strtok('[]'));

        $header = substr($command, 0, strpos($command, '\n') + 2);

        $separators = array_slice($separators, 0, count($separators) - 2);

        return array($header, $separators);
    }

    function headerAndCustomSeparators($command)
    {
        $command = str_replace(array('//'), '', $command);

        list($separator, $command) = explode('\n', $command);

        return array("//$separator\n", array('\n',$separator));
    }

}