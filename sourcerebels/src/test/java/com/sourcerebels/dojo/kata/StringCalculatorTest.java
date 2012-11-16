package com.sourcerebels.dojo.kata;

import junit.framework.TestCase;

public class StringCalculatorTest extends TestCase {

	public void testAdd() throws Exception {
		
		StringCalculator calculator = new StringCalculator();
		
		assertTrue(calculator.add("") == 0);
		
		assertTrue(calculator.add("1") == 1);
		
		assertTrue(calculator.add("1,1") == 2);
		assertTrue(calculator.add("2,2") == 4);
	}
	
	public void testAddUnknownAmountOfNumbers() throws Exception {
		
		StringCalculator calculator = new StringCalculator();
		
		assertTrue(calculator.add("1,1,1") == 3);
		assertTrue(calculator.add("2,2,2") == 6);
		
		assertTrue(calculator.add("1,1,1,1") == 4);
		assertTrue(calculator.add("2,2,2,2") == 8);
		
		assertTrue(calculator.add("1,1,1,1,1") == 5);
		assertTrue(calculator.add("2,2,2,2,2") == 10);
	}
	
	public void testAddLineReturns() throws Exception {
		
		StringCalculator calculator = new StringCalculator();
		
		assertTrue(calculator.add("1\n1,1") == 3);
	}
	
	public void testDifferentDelimiters() throws Exception {
		
		StringCalculator calculator = new StringCalculator();

		System.out.println("res: " + calculator.add("//;\n1;1;1"));
		assertTrue(calculator.add("//;\n1;1;1") == 3);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}

}
