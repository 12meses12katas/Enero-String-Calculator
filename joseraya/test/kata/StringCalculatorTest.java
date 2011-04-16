package kata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	private StringCalculator calculator;
	
	@Before
	public void init() {
		calculator = new StringCalculator();
	}
	
	@Test
	public void testEmptyString() {
		assertEquals(0, calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(5, calculator.add("5"));
	}
	
	@Test
	public void testTwoNumbers() {
		assertEquals(4, calculator.add("2,2"));
	}

	@Test
	public void testThreeNumbers() {
		assertEquals(6, calculator.add("1,2,3"));
	}
	
	@Test
	public void testWeirdSeparators() {
		assertEquals(6, calculator.add("1\n2/3"));
	}
	
	@Test
	public void testCustomDelimiter() {
		assertEquals(6, calculator.add("//.\n1.2.3"));
	}
	
	@Test
	public void testFailForNegatives() {
		try {
			calculator.add("-1,-2");
			fail("Should have thrown an exception because of negative numbers");
		} catch (IllegalArgumentException e) {
			assertTrue("-1 is in the message", e.getMessage().indexOf("-1") >= 0);
			assertTrue("-2 is in the message", e.getMessage().indexOf("-2") >= 0);
			assertTrue("-3 is in the message", e.getMessage().indexOf("-2") >= 0);
		}
	}
}
