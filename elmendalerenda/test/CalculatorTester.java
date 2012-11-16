import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;


public class CalculatorTester {

	private Calculator calculator; 
	
	@Before
	public void setUp(){
		calculator = new Calculator();
	}
	
	@Test
	public void addingEmptyStringIsZero(){
		
		int actual = calculator.add("");
		int expected = 0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void addingANumber(){
		
		int actual = calculator.add("1");
		int expected = 1;
		
		assertEquals("resultado", expected, actual);
	
		actual = calculator.add("12");
		expected = 12;
		
		assertEquals("resultado", expected, actual);
	
	}
	
	@Test
	public void addingTwoNumbers(){
		
		int actual = calculator.add("0,1");
		int expected = 1;
		
		assertEquals("resultado", expected, actual);
	
		actual = calculator.add("1,2");
		expected = 3;
		
		assertEquals("resultado", expected, actual);
	}
	
	@Test
	public void addingNNumbers(){
		
		int actual = calculator.add("0,1,3");
		int expected = 4;
		
		assertEquals("resultado", expected, actual);
	
		actual = calculator.add("1,2,100");
		expected = 103;
		
		assertEquals("resultado", expected, actual);

		actual = calculator.add("1,1,1,1,1,1,1,1,1,1");
		expected = 10;
		
		assertEquals("resultado", expected, actual);
	}

	@Test
	public void addingNumbersWithNewLineSeparator(){
		
		int actual = calculator.add("0\n1\n1");
		int expected = 2;
		
		assertEquals("resultado", expected, actual);
		
		actual = calculator.add("0,1\n1");
		expected = 2;
		
		assertEquals("resultado", expected, actual);		
	
	}

	@Test
	public void addingNumbersCustomSeparador(){
		
		String customSeparator = "elmenda";
		
		String addends = "//[#]\n0#1,2#10".replaceAll("#", customSeparator);
		
		int actual = calculator.add(addends);
		int expected = 13;
		
		assertEquals("resultado", expected, actual);		
	}

	@Test
	public void ignoreNumbersBiggerThan1000(){
		
		int actual = calculator.add("1000,1");
		int expected = 1001;
		
		assertEquals("resultado", expected, actual);		

		actual = calculator.add("1001,1");
		expected = 1;
		
		assertEquals("resultado", expected, actual);		

		actual = calculator.add("10001,1");
		expected = 1;
		
		assertEquals("resultado", expected, actual);		

		actual = calculator.add("10001,1001");
		expected = 0;
		
		assertEquals("resultado", expected, actual);		
	}
	
	@Test
	public void addWithMultipleDelimiters(){
			
		String aCustomSeparator = "elmenda";
		String anotherCustomSeparator = "eraseunavez";
		
		String addendsWith3Delimiters = "//[#][@]\n0#1,2@10".replaceAll("#", aCustomSeparator).replaceAll("@", anotherCustomSeparator);
		
		int actual = calculator.add(addendsWith3Delimiters);
		int expected = 13;
		
		assertEquals("resultado", expected, actual);				
	}
	
	@Test(expected = NegativesNotAllowedException.class)
	public void negativesNotAllowed(){
		calculator.add("-1,1");
		
	}
	
	@Test()
	public void negativesNotAllowedAreShownInExceptionMessage(){
		
		try{
			calculator.add("-1,3,-2,1");
			
		}catch(NegativesNotAllowedException ex)
		{
			assertTrue(ex.getMessage().contains("-1"));
			assertTrue(ex.getMessage().contains("-2"));
			
		}
	}
	
}
