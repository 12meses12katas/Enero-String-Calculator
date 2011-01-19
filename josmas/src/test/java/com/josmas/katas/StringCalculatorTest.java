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
		assertEquals(-1, stcal.add("-1,0"));
		assertEquals(2, stcal.add("1\n1"));
		assertEquals(-1, stcal.add("-1\n0"));
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
		assertEquals(3, stcal.add("//;\n1;2"));
		assertEquals(3, stcal.add("//p\n1p2"));
	}
}
