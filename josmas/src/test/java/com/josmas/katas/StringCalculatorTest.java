package com.josmas.katas;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class StringCalculatorTest {
	StringCalculator stcal;

	@Before
	public void setUp(){
		stcal = new StringCalculator();
	}

	@Test
	public void testAddNoArguments(){
		assertEquals(0, stcal.add(""));
		assertEquals(0, stcal.add("   "));
	}

	@Test
	public void testAddOneArgument(){
		assertEquals(1, stcal.add("1"));
	}

	@Test
	public void testTwoArguments(){
		assertEquals(2, stcal.add("1,1"));
		assertEquals(2, stcal.add("1\n1"));
	}

	@Test
	public void testMoreThanTwoArguments(){
		assertEquals(3, stcal.add("1,1,1"));
		assertEquals(3, stcal.add("1\n1,1"));
		assertEquals(4, stcal.add("1,1,1,1"));
		assertEquals(5, stcal.add("1,1,1,1,1"));
		assertEquals(5, stcal.add("1,1,1,1\n1"));
	}

	@Test
	public void testPassingTheWrongSeparator(){
		try {
			stcal.add("1+1");
			fail("Exception expected!");
		}
		catch (NumberFormatException nfe){
			//Ignoring
		}
	}

	@Test
	public void testHandlingAnySeparator(){
		assertEquals(6, stcal.add("//;\n1;2\n3"));
		assertEquals(6, stcal.add("//p\n1p2,3"));
	}
	
	@Test
	public void testNegativesNotAllowed(){
		try {
			stcal.add("1,-1");
			fail("Illegal Argument Exception expected!");
		}
		catch (IllegalArgumentException iae){
			assertTrue(iae.getMessage().contains("-1"));
		}
		
		try {
			stcal.add("//p\n-1,-1\n-9p-3");
			fail("Illegal Argument Exception expected!");
		}
		catch (IllegalArgumentException iae){
			assertTrue(iae.getMessage().contains("-1, -1, -9, -3"));
		}
	}
}
