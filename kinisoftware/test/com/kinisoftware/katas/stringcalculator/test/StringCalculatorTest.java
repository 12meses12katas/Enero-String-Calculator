package com.kinisoftware.katas.stringcalculator.test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.kinisoftware.katas.stringcalculator.StringCalculator;

public class StringCalculatorTest {

	@Test
	public void emptyString() throws Exception {
		assertThat(StringCalculator.add(""), is(0));
	}
	
	@Test
	public void stringWithOneNumber() throws Exception {
		assertThat(StringCalculator.add("1"), is(1));
		assertThat(StringCalculator.add("2"), is(2));
	}
	
	@Test
	public void stringWithTwoNumbers() throws Exception {
		assertThat(StringCalculator.add("1,2"), is(3));
		assertThat(StringCalculator.add("2,3"), is(5));
	}
	
	@Test
	public void stringWithFewerNumbers() throws Exception {
		assertThat(StringCalculator.add("1,2,3"), is(6));
	}
	
	@Test
	public void stringWithCustomDelimiter() throws Exception {
		assertThat(StringCalculator.add("//\n1\n2"), is(3));
		assertThat(StringCalculator.add("//*1,2*3"), is(6));
	}
	
	@Test
	public void stringWithOneNegativeNumber() throws Exception {
		try {
			StringCalculator.add("1,-2");
			fail("should be fail - not allowed negative numbers");
		} catch( IllegalArgumentException ex) {
			assertThat(ex.getMessage(), is("not allowed negative numbers: -2 "));
		}
	}
	
	@Test
	public void stringWithFewerNegativeNumbers() throws Exception {
		try {
			StringCalculator.add("1,-2,3,-4");
			fail("should be fail - not allowed negative numbers");
		} catch( IllegalArgumentException ex) {
			assertThat(ex.getMessage(), is("not allowed negative numbers: -2 -4 "));
		}
	}
}
