package com.kata.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringCalculatorTest {

	private StringCalculator stringCalculator = new StringCalculator();;

	@Test
	public void shouldBeZeroOnEmptyString() {

		assertEquals(0, stringCalculator.add(""));
	}

	@Test
	public void shouldBeSameOneValue() {

		assertEquals(2, stringCalculator.add("2"));
	}

	@Test
	public void shouldSumMultipleValuesCommaDelimited() {

		assertEquals(3, stringCalculator.add("1,2"));
	}

	@Test
	public void shouldSumMultipleValuesBreakLineDelimited() {

		assertEquals(3, stringCalculator.add("1\n2"));
	}

	@Test
	public void shouldSumMultipleValuesStartsWith2LinesAndSemiColonDelimited() {

		assertEquals(3, stringCalculator.add("1\n2"));
	}
	@Test
	public void shouldErrorNegativeValue() {

		try {
			stringCalculator.add("-1");
			fail();
		} catch (Exception e) {
			assertEquals("-1", e.getMessage());
		}

	}
	@Test
	public void shouldErrorSumNegativeValues() {

		try {
			stringCalculator.add("-1,2,-2");
			fail();
		} catch (Exception e) {
			assertEquals("-1,-2", e.getMessage());
		}

	}
}
