package sorac.co.uk.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest {

    private StringCalculator stringCalculator = new StringCalculator();

    
    @Test(expected=NullPointerException.class)
    public void ifNullShouldThrowException() {
        stringCalculator.add(null);
    }
    
    @Test
    public void withEmptyStringShouldReturnZero() {
        assertEquals(Integer.valueOf(0), stringCalculator.add(""));
    }
    
    @Test
    public void withOneNumberShouldReturnTheSameNumber() {
        assertEquals(Integer.valueOf(1), stringCalculator.add("1"));
    }
    
    @Test
    public void withTwoNumbersShouldReturnTheSum() {
        assertEquals(Integer.valueOf(3), stringCalculator.add("1,2"));
        assertEquals(Integer.valueOf(5), stringCalculator.add("2,3"));
    }
    
    @Test
    public void shouldWorkWithAnyAmountOfNumbers() {
        assertEquals(Integer.valueOf(5), stringCalculator.add("1,1,3"));
    }
    
    @Test
    public void mayContainNewLines() {
        assertEquals(Integer.valueOf(6), stringCalculator.add("1\n2,3")); 
    }
    
    @Test
    public void shouldAllowChangeDelimiter() {
        assertEquals(Integer.valueOf(3), stringCalculator.add("//;\n1;2"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldGetAnExceptionWithNegatives() {
        assertEquals(Integer.valueOf(5), stringCalculator.add("2,-3,3,4,-9"));        
    }
    
    @Test
    public void numbersBiggerThan100ShouldBeIgnored() {
        assertEquals(Integer.valueOf(2), stringCalculator.add("2,1001"));
    }
    
}

