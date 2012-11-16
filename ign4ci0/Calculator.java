package es.ign4ci0.kata.stringCalculator;

import java.util.HashSet;
import java.util.Set;

public class Calculator
{

    public String Add ( String numbers )
    {
        Integer result = Integer.valueOf ( 0 );
        
        if ( !numbers.isEmpty ( ) )
        {
            String delimiters = extractDelimiters ( numbers );
            String numbersWithoutDelimiterDefinition = removeDelimiterDefinition ( numbers );
            Set < Integer > operands = extractOperands ( numbersWithoutDelimiterDefinition, delimiters );
            
            for ( Integer operand : operands )
            {
                result += operand;
            }
        }
        
        return result.toString ( );
    }

    private String removeDelimiterDefinition ( final String numbers )
    {
        String operands = numbers;
        
        if ( numbers.contains ( "//" ) )
        {
            operands = numbers.substring ( numbers.indexOf ( "\n" ) + 1 );
        }
        
        return operands;
    }

    private Set < Integer > extractOperands ( final String numbers, final String delimiters )
    {
        String [ ] operands = numbers.split ( delimiters );
        String illegalArgumentMessage = "";
        Set < Integer > integers = new HashSet < Integer > ( );
        
        for ( String operand : operands )
        {
            Integer integer = Integer.valueOf ( operand );
            
            if ( integer < 0 )
            {
                illegalArgumentMessage = illegalArgumentMessage + "\nnegatives not allowed - " + integer;
            }
            else
            {
                if ( integer <= 1000 )
                {
                    integers.add ( integer );
                }
            }
        }
        
        if ( !illegalArgumentMessage.isEmpty ( ) )
        {
            throw new IllegalArgumentException ( illegalArgumentMessage );
        }
        
        return integers;
    }

    private String extractDelimiters ( final String numbers )
    {
        String delimiters = "[,\\n]+";
        if ( numbers.contains ( "//" ) )
        {
            String newDelimiter = numbers.substring ( 2, numbers.indexOf ( "\n" ) );
            delimiters = "[" + newDelimiter + "\\n]+";
        }
        
        return delimiters;
    }
    
}
