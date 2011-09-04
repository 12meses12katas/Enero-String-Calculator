package com.stringcalculator.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.stringcalculator.src.StringCalculator;

public class StringCalculatorTest
{
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void addMaxTwoOperandsTest() throws Exception
	{
		assertEquals(0, StringCalculator.add(""));
		assertEquals(1, StringCalculator.add("1"));
		assertEquals(3, StringCalculator.add("1,2"));
	}

	@Test
	public void addUnknownOperandsTest() throws Exception
	{
		assertEquals(7, StringCalculator.add("1,5,1"));
		assertEquals(14, StringCalculator.add("1,5,1,3,4"));
	}

	@Test
	public void returnsAndCommansOperatorsTest() throws Exception
	{
		assertEquals(6, StringCalculator.add("1\n2,3"));
		assertEquals(9, StringCalculator.add("1,5\n1,2"));
	}

	@Test
	public void changeDelimiterTest() throws Exception
	{
		assertEquals(3, StringCalculator.add("//;\n1;2"));
		assertEquals(10, StringCalculator.add("//:\n1:2,3\n2:2"));
	}

	@Test
	public void negativeNumbersExceptionTest() throws Exception
	{
		exception.expectMessage("negatives not allowed -2");
		StringCalculator.add("//;\n1;-2");
		exception.expectMessage("negatives not allowed -42 -4 -1");
		StringCalculator.add("//;\n1;-42;-4;-1");
	}
	
	@Test
	public void operandsLessThanThousandTest() throws Exception
	{
		assertEquals(2, StringCalculator.add("2,1001"));
		assertEquals(0, StringCalculator.add("1540,1425"));
		assertEquals(0, StringCalculator.add("1540"));
	}
	
	@Test
	public void anyLengthDelimiterTest() throws Exception
	{
		assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
	}
	
	@Test
	public void multipleDelimitersTest() throws Exception
	{
		assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
	}
	
	@Test
	public void anyLengthMultipleDelimitersTest() throws Exception
	{
		assertEquals(6, StringCalculator.add("//[***][%]\n1***2%3"));
	}
}
