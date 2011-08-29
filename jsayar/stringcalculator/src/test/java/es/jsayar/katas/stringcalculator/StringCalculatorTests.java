/*
 * This file is part of StringCalculator
 *
 * Copyright (C) 2011 Jesus Miguel Sayar Celestino
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package es.jsayar.katas.stringcalculator;

import static org.junit.Assert.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Jesus Miguel Sayar Celestino <xuxoceleste@gmail.com>
 * 
 */
public class StringCalculatorTests {

	StringCalculator calculator;

	@Before
	public void setUp() {
		calculator = new StringCalculator();
	}

	@Test
	public void emptyString() {
		Assert.assertEquals(0, calculator.add(""));
	}

	@Test
	public void stringWithOneNumber() {
		Assert.assertEquals(1, calculator.add("1"));
	}

	@Test
	public void stringWithTwoNumbers() {
		Assert.assertEquals(3, calculator.add("1,2"));
	}

	@Test
	public void stringWithFewerNumbers() {
		Assert.assertEquals(6, calculator.add("1,2,3"));
	}

	@Test
	public void stringWithNewLineDelimiter() {
		Assert.assertEquals(6, calculator.add("1\n2,3"));
	}

	@Test
	public void stringWithCustomDelimiter() {
		Assert.assertEquals(3, calculator.add("//;\n1;2"));
		Assert.assertEquals(6, calculator.add("//*\n1*2*3"));
	}

	@Test
	public void stringWithNegativeNumber() {
		try {
			calculator.add("-1");
			fail("should be fail - egatives not allowed");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(e.getMessage(), "negatives not allowed: -1 ");

		}
	}

	@Test
	public void stringWithFewerNegativeNumbers() {
		try {
			calculator.add("-1,-2,-3");
			fail("should be fail - egatives not allowed");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals(e.getMessage(),
					"negatives not allowed: -1 -2 -3 ");

		}
	}

}
