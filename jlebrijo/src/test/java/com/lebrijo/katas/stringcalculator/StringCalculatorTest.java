package com.lebrijo.katas.stringcalculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest {
	StringCalculator calculator = new StringCalculator();

	private void checkAdd(String string, int expected) {
		assertEquals(expected, calculator.add(string));
	}

	@Test
	public void shouldReturnBasicValues() {
		checkAdd("", 0);
		checkAdd("1", 1);
	}

	@Test
	public void shouldReturnSum() throws Exception {
		checkAdd("1,2", 3);
		checkAdd("1,2,5,4,1", 13);
	}

	@Test
	public void allowNewLineAsDelimiter() throws Exception {
		checkAdd("1\n2,3", 6);
	}
	
	@Test
	public void allowAnyDelimiter() throws Exception {
		checkAdd("//;\n1;3", 4);
	}
	
	@Test
	public void exceptionWithNegatives() throws Exception {
		try {
		checkAdd("-3,2,-23,5", 0);
		} catch (Exception e) {
			assertEquals("Negatives Not Allowed: -3,-23",
					e.getMessage());
		}
	}
}
