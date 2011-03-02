package com.japaz.katas.esc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.japaz.katas.esc.INumericParser;
import com.japaz.katas.esc.NumericParserImpl;
import com.japaz.katas.esc.INumericAdder;
import com.japaz.katas.esc.NumericAdderImpl;
import com.japaz.katas.esc.StringCalculator;

public class TestStringCalculator {
	StringCalculator sc = null;
	
	@Before
	public void setup() {
		INumericParser parser = new NumericParserImpl();
		INumericAdder adder = new NumericAdderImpl();
		sc = new StringCalculator(parser, adder);
	}

	@Test
	public void testEmptyString() throws Exception  {
		int result = sc.add("");
		
		assertEquals(0, result);		
	}
	
	@Test
	public void testOneNumber() throws Exception  {
		int result = sc.add("2");
		assertEquals(2, result);
	}

	@Test
	public void testTwoNumbers() throws Exception  {
		int result = sc.add("2,4");
		assertEquals(6, result);
	}

	@Test
	public void testMoreNumbers() throws Exception  {
		int result = sc.add("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20");
		assertEquals(210, result);
	}
	
	@Test
	public void testNewLineNumbers() throws Exception {
		int result = sc.add("1,2\n3\n4,5,6,7,8,9,10,11\n12,13,14,15\n16,17,18,19,20");
		assertEquals(210, result);
	}
	
	@Test
	public void testMalformedNewLikeNumbers() {
		try {
			sc.add("1,\n");
			fail();
		} catch (Exception e) {
			;
		}
	}
	
	@Test
	public void testNegativeValues() {
		try {
			sc.add("-1,3,4,-5");
			fail();
		} catch (Exception e) {
			e.getMessage().equals("negatives not allowed: -1 -5");
		}
	}
}
