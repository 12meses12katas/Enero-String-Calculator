package com.josmas.katas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest{
	private StringCalculator sc;
	
	@Before
	public void setUp(){
		sc = new StringCalculator();
	}
	
	@Test
	public void testEmptyArgument(){
		assertEquals(0, sc.add(""));
		assertEquals(0, sc.add("  "));
	}
	
	@Test
	public void testOneArgument(){
		assertEquals(0, sc.add("0"));
		assertEquals(9, sc.add("9"));
	}
	
	@Test
	public void testAnyNumberOfArgumentsWithCommas(){
		assertEquals(1, sc.add("0,0,0,0,1"));
		assertEquals(10, sc.add("1,2,3,0,4"));
	}
	
	@Test
	public void testAnyNumberOfArgumentsWithCommasAndNewLines(){
		assertEquals(1, sc.add("0,0,0\n0,1"));
		assertEquals(10, sc.add("1,2,3\n0,4"));
	}
	
	@Test
	public void testAnyNumberOfArgumentsWithNewDelimiters(){ // format “//[delimiter]\n”
		assertEquals(1, sc.add("//[*]\n0,0*0\n0,1"));
		assertEquals(10, sc.add("//[;]\n1,2;3\n0,4"));
	}
	
	@Test
	public void testRegularExpression(){
		String [] splitResult = "//[*]\n0,0*0\n0,-1,0,-3".split("[^-0-9]");
		for (String string : splitResult) {
			System.out.println("We get a: " + string);
		}
	}
	
	@Test
	public void testNegativesNotAllowedAndReported(){
		try {
			sc.add("//[*]\n0,0*0\n0,-1,0,-3");
			fail("Illegal Argument Exception expected!");
		}
		catch (IllegalArgumentException iae) {
			assertTrue(iae.getMessage().contains("-1, -3"));
		}
	}
	
	@Test
	public void testNumbersBiggerThan1000AreIgnored(){
		assertEquals(1, sc.add("//[*]\n0,1001*11110\n0,1"));
		assertEquals(10, sc.add("//[;]\n1,2;3\n111110,4,900000"));
	}
	
	@Test
	public void testDelimitersOfAnyLegth(){
		assertEquals(1, sc.add("//[*********]\n0*********1001*********11110\n0,1"));
		assertEquals(10, sc.add("//[;;;]\n1,2;;;3\n111110,4;;;900000"));
	}
	
	@Test
	public void testAllowMultipleDelimitersOfAnyLegth(){
		assertEquals(1, sc.add("//[*********][;;;]\n0*********1001*********11110\n0;;;1,0"));
		assertEquals(10, sc.add("//[;;;][%%]\n1%%2;;;3\n111110,4;;;900000,0"));
	}
	
}