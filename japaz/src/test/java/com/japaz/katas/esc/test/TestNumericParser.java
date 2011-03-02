package com.japaz.katas.esc.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.*;

import com.japaz.katas.esc.INumericParser;
import com.japaz.katas.esc.NumericParserImpl;


public class TestNumericParser {
	INumericParser parser;
	
	@Before
	public void setUp() {
		parser = new NumericParserImpl();
	}
	
	@Test
	public void testEmptyString() {
		List<Integer> result = parser.parse("");
		assertEquals(0, result.size());
	}
	
	@Test
	public void testOneNumber() {
		List<Integer> result = parser.parse("3");
		assertEquals(1, result.size());
		assertThat(result, hasItem(3));
	}

	@Test
	public void testTwoNumbers() {
		List<Integer> result = parser.parse("3,5");
		assertEquals(2, result.size());
		assertThat(result, hasItem(3));
		assertThat(result, hasItem(5));
	}
	
	@Test
	public void testNewLineNumbers() {
		List<Integer> result = parser.parse("1\n2,3");
		assertEquals(3, result.size());
		assertThat(result, hasItem(1));
		assertThat(result, hasItem(2));
		assertThat(result, hasItem(3));
	}
	
	@Test
	public void testMalformedNewLineNumbers() {
		try {
			parser.parse("1,\n");
			fail();
		} catch (Exception e) {
			;
		}
	}

	@Test
	public void testDelimiterNumbers() {
		List<Integer> result = parser.parse("//;\n1;2;3");
		assertEquals(3, result.size());
		assertThat(result, hasItem(1));
		assertThat(result, hasItem(2));
		assertThat(result, hasItem(3));
	}
	
	@Test
	public void testMalformedDelimiterNumbers() {
		try {
			parser.parse("2;//;\n1;2;3");
			fail();
		} catch (Exception e) {
			;
		}
	}
	
	@Test
	public void testLongDelimiterNumbers() {
		List<Integer> result = parser.parse("//[***]\n1***2***3");
		assertEquals(3, result.size());
		assertThat(result, hasItem(1));
		assertThat(result, hasItem(2));
		assertThat(result, hasItem(3));
	}

	@Test
	public void testMultipleLongDelimiterNumbers() {
		List<Integer> result = parser.parse("//[***][%%%]\n1***2%%%3");
		assertEquals(3, result.size());
		assertThat(result, hasItem(1));
		assertThat(result, hasItem(2));
		assertThat(result, hasItem(3));
	}

}
