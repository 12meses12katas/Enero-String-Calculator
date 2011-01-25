package com.josmas.katas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

	private StringCalculator sc;
	@Before
	public void setUp() throws Exception {
		sc = new StringCalculator();
	}

	@Test
	public void testEmptyArgument(){
		assertEquals(0, sc.add(""));
		assertEquals(0, sc.add("  "));
	}
	
	@Test
	public void testOneArgumentwithCommas(){
		assertEquals(1, sc.add("1"));
		assertEquals(1, sc.add("  1"));
	}
	
	@Test
	public void testTwoArgumentswithCommas(){
		assertEquals(3, sc.add("1,2"));
		assertEquals(3, sc.add("  1,2 "));
	}
	
	@Test
	public void testAnyNumberOfArgumentswithCommas(){
		assertEquals(3, sc.add("1,2,0,0,0"));
		assertEquals(3, sc.add("  1,2,0 "));
	}
	
	@Test
	public void testAnyNumberOfArgumentswithCommasAndNewLines(){
		assertEquals(3, sc.add("1\n2,0,0\n0"));
		assertEquals(3, sc.add("  1,2\n0 "));
	}
	
	@Test
	public void testAnyNumberOfArgumentsWithAnyDelimiter(){
		assertEquals(3, sc.add("//p\n1\n2,0p0\n0"));
		assertEquals(3, sc.add(" //;\n1;2\n0 "));
	}
	
	@Test
	public void testNoNegativesAllowed(){
		try {
			sc.add("//p\n-1\n2,0p0\n0");
			fail("No negatives allowed");
		}
		catch (IllegalArgumentException iae){
			System.out.println(iae.getMessage());
			assertTrue(iae.getMessage().contains("-1"));
		}
		
		try {
			sc.add(" //;\n-1;-2\n0 ");
			fail("No negatives allowed");
		}
		catch (IllegalArgumentException iae){
			System.out.println(iae.getMessage());
			assertTrue(iae.getMessage().contains("-1, -2"));
		}
	}
	
	@Test
	public void testAnyNumberSmallerThan1000WithAnyDelimiter(){
		assertEquals(3, sc.add("//p\n1\n2,0p0\n0\n1001p9000"));
		assertEquals(3, sc.add(" //;\n1;7890,2\n0 "));
	}
	
	@Test
	public void testDelimitersOfAnyLength(){
		assertEquals(4, sc.add("//[***]\n1\n2,0,0***1"));
		assertEquals(4, sc.add("//[oooo]\n1\n2,0,0oooo1"));
	}
	
	@Test
	public void testMoreThanOneDelimitersOfAnyLength(){
		assertEquals(9, sc.add("//[***][%%]\n1\n2,0,0***1%%5"));
		assertEquals(9, sc.add("//[***][uuuuuu]\n1uuuuuu2,0,0***1uuuuuu5"));
	}

}
