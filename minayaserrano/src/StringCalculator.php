<?php
class StringCalculator
{
    private $stringParser;
    
    public function __construct(StringParser $stringParser)
    {
        $this->stringParser = $stringParser;
    }
    
    public function add($stringNumbers)
    {
        if($stringNumbers == "")
            return 0;

        return array_sum($this->stringParser->parse($stringNumbers));
    }
}
