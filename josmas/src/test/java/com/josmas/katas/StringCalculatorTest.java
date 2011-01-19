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
	}

	@Test
	public void testAddOneArgument(){
		assertEquals(1, stcal.add("1"));
	}

	@Test
	public void testTwoArguments(){
		assertEquals(2, stcal.add("1,1"));
		assertEquals(-1, stcal.add("-1,0"));
	}

	@Test
	public void testMoreArgumentsThanPermittedSofar(){
		try {
			stcal.add("1,1,1");
			fail("Exception expected!");
		}
		catch (IllegalArgumentException iae) {
			//Ignoring		
		}
	
	}
}
