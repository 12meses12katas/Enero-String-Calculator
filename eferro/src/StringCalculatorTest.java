//import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;


public class StringCalculatorTest  {
	StringCalculator stringCalculator = null;
	
	@Before 
	public void setUp() {
		stringCalculator = new StringCalculator();
	}

	@Test
	public void testAddNullStringReturnsCero(){
		assertEquals((Integer)0, stringCalculator.addStrings(null));
	}
	
	@Test
	public void testAddEmptyStringReturnsCero(){
		assertEquals((Integer)0, stringCalculator.addStrings(""));
	}
	
	@Test
	public void testAddOneNumber(){

		assertEquals((Integer)10, stringCalculator.addStrings("10"));
		assertEquals((Integer)0, stringCalculator.addStrings("0"));
		assertEquals((Integer)(5), stringCalculator.addStrings("5"));
	}
	
	@Test
	public void testAddTwoNumbers() {
		assertEquals((Integer)10, stringCalculator.addStrings("4,6"));
		assertEquals((Integer)8, stringCalculator.addStrings("2,6"));
		assertEquals((Integer)(11), stringCalculator.addStrings("5\n6"));
	}
	
	@Test
	public void testAddMoreThanTwoNumbers(){
		assertEquals((Integer)10, stringCalculator.addStrings("2,2,6"));
		assertEquals((Integer)14, stringCalculator.addStrings("4,6,2\n2"));
	}
	
	@Test
	public void testNewLineAsSeparator(){
		assertEquals((Integer)6, stringCalculator.addStrings("1\n2,3"));
	}
	
	public void testUserDefinedDelimiter() {
		assertEquals((Integer)3, stringCalculator.addStrings("//;\n1;2"));
		assertEquals((Integer)5, stringCalculator.addStrings("//_\n1_2_2"));
	}


	@Test(expected=NegativesNotAllowed.class)
	public void testNegativesNotAllowed() throws NegativesNotAllowed {
		stringCalculator.addStrings("-2");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testNegativesNotAllowed2() throws NegativesNotAllowed {
		thrown.expect(NegativesNotAllowed.class);
		thrown.expectMessage("Values -2 -4");
		
		stringCalculator.addStrings("-2,3,-4");
		
	}

}
