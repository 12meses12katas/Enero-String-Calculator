
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	
	private StringCalculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new StringCalculator();
	}
	
	@Test
	public void emptyStringReturnZero(){	
		int expected = 0;

		int actual = calculator.add("");

		assertEquals(expected, actual);
	}
	
	@Test
	public void addWithANumber(){
		int expected = 2;
		int expected2 = 35;
		
		int actual = this.calculator.add("2");
		int actual2 = this.calculator.add("35");
		
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void addWithTwoNumbers(){
		int expected = 3;
		int expected2 = 8;
		
		int actual = this.calculator.add("2,1");
		int actual2 = this.calculator.add("3,5");

		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void addWithUnknownNumbers(){
		int expected = 4;
		int expected2 = 54;
		
		int actual = this.calculator.add("2,1,1");
		int actual2 = this.calculator.add("3,5,5,41");
		
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void addWithMultipleDelimiters(){
		int expected = 4;
		int expected2 = 54;
		
		int actual = this.calculator.add("2\n1\n1");
		int actual2 = this.calculator.add("3,5,5\n41");
		
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void addWithCustomDelimiter(){
		String withCustomDelimiter = "//[pereta]\n1pereta2pereta2";
		int expected = 5;
		
		int actual = calculator.add(withCustomDelimiter);
		
		assertEquals(expected, actual);	
	}
	
	@Test(expected = NegativesNotAllowedException.class)
	public void addWithNegativeNumbers(){
		calculator.add("5,1,-4,-77");	
	}
	
	@Test
	public void addWithNegativeNumbersMessage(){
		try{
			calculator.add("5,1,-4,-77");
		}
		catch (NegativesNotAllowedException ne) {
			assertTrue(ne.getMessage().contains("-4") && ne.getMessage().contains("-77"));
			return;
		}
		fail(NegativesNotAllowedException.class.getName()+" deberia haber sido lanzada.");
	}
	
	@Test
	public void addWithMaxNumber(){
		int expected = 2;
		
		int actual = this.calculator.add("2,1001");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void addWithMultipleCustomDelimiters(){
		String withMultipleCustomDelimiter = "//[pereta][hola]\n30pereta20pereta2hola5";
		int expected = 57;
		
		int actual = calculator.add(withMultipleCustomDelimiter);
		
		assertEquals(expected, actual);	
	}
}
