package com.farmerdev.katas.stringcalculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringCalculatorTest {
	private static StringCalculator stringCalculator = null;
	
	@BeforeClass
	public static void initEveryTest(){
		stringCalculator = new StringCalculator();

	}
	@Test
	public void when_empty_string_then_should_return_zero(){
		
		int result = stringCalculator.add("");
		
		assertThat(result, is(0));
	}
	
	
	
}
