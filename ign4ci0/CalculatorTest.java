package es.ign4ci0.kata.stringCalculator.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import es.ign4ci0.kata.stringCalculator.Calculator;

public class CalculatorTest
{
    private Calculator _calculator = null;
    
    @Before
    public void initializeCalculator ( )
    {
        _calculator = new Calculator ( );
    }
    
    @Test
    public void passingEmptyStringShouldReturnZero ( )
    {
        String result = _calculator.Add ( "" );
        
        Assert.assertEquals ( "0", result );
    }
    
    @Test
    public void passsingOneNumberShouldReturnThatNumber ( )
    {
        String result = _calculator.Add ( "1" );
        
        Assert.assertEquals ( "1", result );
    }
    
    @Test
    public void passsingTwoNumbersShouldReturnTheirSum ( )
    {
        String result = _calculator.Add ( "1,2" );
        
        Assert.assertEquals ( "3", result );
    }
    
    @Test
    public void passsingAnotherTwoNumbersShouldReturnTheirSum ( )
    {
        String result = _calculator.Add ( "3,2" );
        
        Assert.assertEquals ( "5", result );
    }
    
    @Test
    public void passsingUnknownAmountOfNumbersShouldReturnTheirSum ( )
    {
        String result = _calculator.Add ( "3,2,4,1" );
        
        Assert.assertEquals ( "10", result );
    }
    
    @Test
    public void passsingNewLinesBetweenNumbersShouldReturnTheirSum ( )
    {
        String result = _calculator.Add ( "3\n2,4,1" );
        
        Assert.assertEquals ( "10", result );
    }
    
    @Test
    public void passsingNumbersWithDifferentDelimiterShouldReturnTheirSum ( )
    {
        String result = _calculator.Add ( "//[;]\n3\n2;4;1" );
        
        Assert.assertEquals ( "10", result );
    }
    
    @Test ( expected = IllegalArgumentException.class )
    public void passsingNegativeNumbersShouldThrowAnException ( )
    {
        try
        {
            String result = _calculator.Add ( "//[;]\n3\n2;-4;-1" );
        
            Assert.assertEquals ( "0", result );
        }
        catch ( IllegalArgumentException e )
        {
            e.printStackTrace ( );
            
            throw e;
        }
    }
    
    @Test
    public void passingMoreThanAThousandNumbersShouldBeIgnored ( )
    {
        String result = _calculator.Add ( "3\n2,1001,1" );
        
        Assert.assertEquals ( "6", result );
    }
    
    @Test
    public void delimitersCanBeOfAnyLength ( )
    {
        String result = _calculator.Add ( "//[*]\n3\n2****4*****1" );
        
        Assert.assertEquals ( "10", result );
    }
    
    @Test
    public void allowMultipleDelimiters ( )
    {
        String result = _calculator.Add ( "//[*][%]\n3\n2*4%1" );
        
        Assert.assertEquals ( "10", result );
    }
    
    @Test
    public void allowMultipleDelimitersWithLengthLongerMoreThanOneCharacter ( )
    {
        String result = _calculator.Add ( "//[*][%]\n3\n2****4%%%%1" );
        
        Assert.assertEquals ( "10", result );
    }
}
