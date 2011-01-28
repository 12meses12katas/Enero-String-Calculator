package com.josmas.katas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest{
	
	StringCalculator sc;
	
	@Before
	public void setUp(){
		sc = new StringCalculator();
	}
	
	@Test
	public void testEmptyArgument(){	
		assertEquals(0, sc.add(""));
		assertEquals(0, sc.add(" "));
	}
	
	@Test
	public void testOneArgument() {
		assertEquals(0, sc.add("0"));
		assertEquals(1, sc.add(" 1 "));
	}
	
	@Test
	public void testArgumentsWithCommas() {
		assertEquals(1, sc.add("0,1"));
		assertEquals(100, sc.add(" 1,98,0,0,1 "));
	}
	
	@Test
	public void testArgumentsWithCommasAndNewLines() {
		assertEquals(1, sc.add("0,1\n0"));
		assertEquals(100, sc.add(" 1,98\n0\n0,1 "));
	}
	
	// Different delimiters with format “//;\n1;2”
	@Test
	public void testArgumentsWithDifferentDelimiters() {
		assertEquals(1, sc.add("//;\n0,1\n0;0"));
		assertEquals(100, sc.add(" //*\n1,98\n0\n0*1 "));
	}
	
	@Test
	public void testNegativesNotAllowed(){
		try {
			sc.add("//;\n0,-1\n0;0;-1001,-2000,-3999");
			fail("Exception expected: Negative numbers not allowed!");
		}
		catch (IllegalArgumentException iae){
			assertTrue(iae.getMessage().contains("-1, -1001, -2000, -3999"));
		}
		
	}
	
	@Test
	public void testNumbersBiggerThan1000AreIgnored(){
		assertEquals(1, sc.add("//;\n0,1\n0;0;1001,2000,3999"));
	}
	
	@Test
	public void testMultipleDelimitersOfAnySize(){
		assertEquals(1, sc.add("//[**][;;;][&&&&]\n0,1\n0&&&&0;1001;;;2000**3999"));
	}
}