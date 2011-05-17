package org.kata.enero;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

	StringCalculator cal;
	
	@Before
	public void setUp() throws Exception {
		cal = new StringCalculator();
	}

	@Test
	public void simpleStringCalculator() throws Exception {
		assertEquals(cal.getValue(), 0);
		
		cal.setNumbers("1");
		assertEquals(cal.getValue(), 1);
		
		cal.setNumbers("1,2");
		assertEquals(cal.getValue(), 3);
	}
	
	@Test
	public void unknownAmountStringCalculator() throws Exception {

		cal.setNumbers("1,2,4,3");
		assertEquals(cal.getValue(), 10);

		cal.setNumbers("17,6,31,9,73, 6,22,4,58");
		assertEquals(cal.getValue(), 226);
		
	}
	
	@Test
	public void newLineStringCalculator() throws Exception {
		
		cal.setNumbers("1\n2,3");
		assertEquals(cal.getValue(), 6);
		
		cal.setNumbers("1, \n");
		assertEquals(cal.getValue(), 1);
		
		cal.setNumbers("1, ");
		assertEquals(cal.getValue(), 1);
		
	}
}
