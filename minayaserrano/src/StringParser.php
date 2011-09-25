<?php
class StringParser
{
    const NEW_LINE              = "\n"; 
    const DEFAULT_DELIMITERS    = "\n,";
    const DEFINE_DELIMITER_LINE = "//";
    const INIT_DEFINE_DELIMITER = "[";
    const END_DEFINE_DELIMITER  = "]";
    
    private $delimiters;
    
    
    public function __construct()
    {
        $this->delimiters = self::DEFAULT_DELIMITERS;
    }

    public function parse($string)
    {
        // Se comprueba si la primera línea es de definición de delimitador
        if($this->checkIfExistsCustomDelimiter($string))
        {
            $this->delimiters = $this->recoverCustomDelimiters($string);
            $string           = $this->removeCustomDelimitatorDefinition($string);
        }
         
        return $this->removeGreaterThan1000($this->checkNegatives(preg_split('/' . self::INIT_DEFINE_DELIMITER . $this->delimiters . self::END_DEFINE_DELIMITER . '/', $string)));
    }
    
    protected function recoverCustomDelimiters($string)
    {
        $customDelimiter = substr($string, 2, 1);
        
        if($customDelimiter != self::INIT_DEFINE_DELIMITER)
            return $customDelimiter;
        
        $customDelimiter = '';
        while(substr($string, 0, 1) != self::NEW_LINE)
        {
            $delimiterInit   = strpos($string, self::INIT_DEFINE_DELIMITER);
            $delimiterFinish = strpos($string, self::END_DEFINE_DELIMITER);
            $delimiterLength = $delimiterFinish - $delimiterInit - 1;

            $customDelimiter .= substr($string, $delimiterInit + 1, $delimiterLength);
            $string = substr($string, $delimiterFinish + 1);
        }
        
        return $customDelimiter;
    }
    
    protected function checkIfExistsCustomDelimiter($string)
    {
        return substr($string, 0, 2) == self::DEFINE_DELIMITER_LINE;
    }
    
    protected function removeCustomDelimitatorDefinition($string)
    {
        return substr($string, strpos($string, self::NEW_LINE));
    }
    
    protected function checkNegatives($arrayNumbers)
    {
        $arrayNegatives = array_filter($arrayNumbers, function ($element) { return $element < 0; });
        if(count($arrayNegatives) > 0)
            throw new InvalidArgumentException('negatives not allowed -> ' . implode(', ', $arrayNegatives));

        return $arrayNumbers;
    }
    
    protected function removeGreaterThan1000($arrayNumbers)
    {
        return array_values(array_filter($arrayNumbers, function ($element) { return $element <= 1000; }));
    }
}
