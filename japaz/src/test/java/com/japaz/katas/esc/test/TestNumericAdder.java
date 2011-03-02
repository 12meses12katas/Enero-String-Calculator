package com.japaz.katas.esc.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.*;

import com.japaz.katas.esc.INumericAdder;
import com.japaz.katas.esc.NumericAdderImpl;


public class TestNumericAdder {
	INumericAdder adder;
	
	@Before
	public void setUp() {
		adder = new NumericAdderImpl();
	}
	
	@Test
	public void testEmptyList() {
		List<Integer> numbers = new ArrayList<Integer>();
		int result = adder.add(numbers);
		assertEquals(0, result);
	}
	
	@Test
	public void testOneNumber() {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		int result = adder.add(numbers);
		assertEquals(4, result);
	}

	@Test
	public void testTwoNumbers() {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(4);
		numbers.add(7);
		int result = adder.add(numbers);
		assertEquals(11, result);
	}
	
	@Test
	public void discardBiggerNumbers() {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1001);
		numbers.add(7);
		int result = adder.add(numbers);
		assertEquals(7, result);
	}
}
