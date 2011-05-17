package org.kata.enero;


import static org.junit.Assert.*;

import java.util.regex.PatternSyntaxException;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

	StringCalculator cal;
	
	@Before
	public void setUp() throws Exception {
		cal = new StringCalculator();
	}

	@Test
	public void simple() throws Exception {
		assertEquals(0, cal.add(""));
		
		assertEquals(1, cal.add("1"));
		
		assertEquals(3, cal.add("1,2"));
	}
	
	@Test
	public void unknownAmount() throws Exception {

		assertEquals(10, cal.add("1,2,4,3"));

		assertEquals(226, cal.add("17,6,31,9,73, 6,22,4,58"));
		
	}
	
	@Test
	public void newLine() throws Exception {
		
		assertEquals(6, cal.add("1\n2,3"));

		assertEquals(20, cal.add("13, 5\n2"));
		
	}
	
	@Test(expected=PatternSyntaxException.class)
	public void newLineException() throws Exception {
		
		assertEquals(1, cal.add("1, \n"));
		
	}
	
	@Test
	public void chageDelimiter() throws Exception {
		
		assertEquals(4, cal.add("//;\n1;3"));
		
	}
	
	@Test(expected=PatternSyntaxException.class)
	public void negativeNumberException() throws Exception {
		
		assertEquals(3, cal.add("1,-3, 5"));
		
	}
	
}
