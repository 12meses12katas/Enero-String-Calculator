import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore 
import org.junit.Test;

class StringCalculatorTest {
    
    StringCalculator calculator;
    
    @Before
    public void setUp() throws Exception {
        calculator = new StringCalculator();
    }
    
    @Test
    public void testEmpty(){
        assertEquals(0, calculator.add(""));
    }
    
    @Test
    public void testOneNumber(){
        assertEquals(1, calculator.add("1"));
        assertEquals(2, calculator.add("2"));
    }
    
    @Test
    public void testTwoNumbers(){
        assertEquals(3, calculator.add("1,2"));
        assertEquals(10, calculator.add("4,6"));
    }
    
    @Test
    public void testSeveralNumbers(){
        assertEquals(10, calculator.add("2,3,5"));
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }
    
    @Test
    public void testMixedWithNewLine(){
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(6, calculator.add("1,2\n3"));
    }
    
    @Test
    public void testIgnoreGreaterThan1000(){
        assertEquals(3, calculator.add("3,1001"));
        assertEquals(3, calculator.add("1001,1,2"));
    }
    
    @Test
    public void testNegativesNotAllowed(){
        try{
            assertEquals(10, calculator.add("-2,1"));
            fail("No negatives allowed")
        } catch (Exception e){
        }
        
        try{
            assertEquals(10, calculator.add("2,-1"));
            fail("No negatives allowed")
        } catch (Exception e){
        }
    }
    
    @Test
    public void testSeveralNegativesNotAllowed(){
        try{
            assertEquals(10, calculator.add("-2,-1,3"));
            fail("No negatives allowed")
        } catch (NegativesException e){
            assertEquals(2, e.getNegatives().size())
            assertEquals([-2, -1], e.getNegatives())
        }
    }
    
    @Test
    public void testDefiningDelimiter(){
        assertEquals(3, calculator.add("//[;]\n1;2"));
        assertEquals(6, calculator.add("//[;]\n1;2;3"));
    }
    
    @Test
    public void testDefiningLongDelimiter(){
        assertEquals(3, calculator.add("//[kkk]\n1kkk2"));
    }
    
    @Test
    @Ignore
    public void testDefiningSeveralDelimiter(){
        assertEquals(6, calculator.add("//[k][q]\n1k2q3"));
    }
}
