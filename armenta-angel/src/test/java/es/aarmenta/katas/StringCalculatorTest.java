package es.aarmenta.katas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringCalculatorTest {

	StringCalculator calc = new StringCalculator();

	@Test
	public void testStringCalculatorBasico() {
		try {
			assertEquals(0, calc.add(""));
			assertEquals(1, calc.add("1"));
			assertEquals(3, calc.add("1,2"));
			assertEquals(6, calc.add("4,2"));
			assertEquals(13, calc.add("4,2,7"));
			assertEquals(33, calc.add("4,2,8,9,10"));
		} catch (Exception notExpected) {
			fail(notExpected.getMessage());
		}
	}

	@Test
	public void testStringCalculatorRetornoCarro() {
		try {
			assertEquals(3, calc.add("1\n2"));
			assertEquals(6, calc.add("4,2"));
			assertEquals(13, calc.add("4,2\n7"));
			assertEquals(33, calc.add("4,2\n8,9\n10"));
		} catch (Exception notExpected) {
			fail(notExpected.getMessage());
		}
	}

	@Test
	public void testStringCalculatorDelimitadores() {
		try {
			assertEquals(3, calc.add("//:\n1:2"));
			assertEquals(6, calc.add("//@\n4@2"));
			assertEquals(13, calc.add("//#\n4#2#7"));
			assertEquals(33, calc.add("//Y\n4Y2Y8Y9Y10"));
		} catch (Exception notExpected) {
			fail(notExpected.getMessage());
		}
	}

	@Test
	public void testStringCalculatorNoNegativos() {
		List<String> testCases = new ArrayList<String>();

		testCases.add("1,-2");
		testCases.add("4,-2\n-7");
		testCases.add("//#\n4#-2#7");
		testCases.add("//Y\n4Y2Y8Y-9Y-10");

		for (int i = 0; i < testCases.size(); i++) {
			try {
				calc.add(testCases.get(i));
				fail();
			} catch (NegativesNotAllowedException expected) {
				System.out.println(expected.getMessage());
				assertTrue(true);
			}
		}
	}
	
	@Test
	public void testStrcalcDelimAnyLength() {
		try {
			assertEquals(6, calc.add("//[***]\n1***2***3"));
			assertEquals(6, calc.add("//[**]\n1**2**3"));
			assertEquals(6, calc.add("//[PPPPP]\n1PPPPP2PPPPP3"));
		} catch (Exception notExpected) {
			fail(notExpected.getMessage());
		}
	}
	
	@Test
	public void testStrcalcDiferentesDelims() {
		try {
			assertEquals(6, calc.add("//[*][%]\n1*2%3"));
			assertEquals(6, calc.add("//[**][Y]\n1Y2**3**1006"));
		} catch (Exception notExpected) {
			fail(notExpected.getMessage());
		}
	}
	
}
