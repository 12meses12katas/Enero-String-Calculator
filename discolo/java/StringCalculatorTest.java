package es.rromero.kata1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;


public class StringCalculatorTest {
	
	@Test
	public void addWithVoidNumber() {
		StringCalculator sc = new StringCalculator();
		assertEquals(0, sc.add(""));
	}
	
	@Test
	public void addWithOneNumber() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2, sc.add("2"));
	}
	
	@Test
	public void addWithTwoNumbers() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2+5, sc.add("//,\n2,5"));
	}
	
	@Test
	public void addWithWrongChar() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2, sc.add("//,\n2,A"));
	}
	
	@Test
	public void addWithNElements() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2+3+4+5+6+7+8, sc.add("//,\n2,3,4,5,6,7,8"));
	}
	
	@Test
	public void addWithNewLineSepparator() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2+3+4+5+6+7+8, sc.add("//,\n2\n3,4,5\n6,7,8"));
	}
	
	@Test
	public void addWithCustomDelimiter() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2+3+4+5+6+7+8, sc.add("//x\n2x3x4\n5x6x7x8"));
	}	
	
	@Test
	public void addWithNegatives() {
		StringCalculator sc = new StringCalculator();
		try {
			sc.add("//x\n2x3x-4\n5x6x7x8");
			fail("An exception must have been thrown");
		} catch (RuntimeException e) {
			assertNotNull(e);
			assertEquals("Negative not allowed", e.getMessage());
		}
	}
	
	@Test
	public void addWithNumberGreaterThan1000() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2, sc.add("1001;2"));
	}	
	
	@Test
	public void addWithAnyLenghtDelimiter() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2+32, sc.add("//[***]\n1001***2***32"));
	}	
	
	@Test
	public void addWithMultipleDelimiters() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2+32+1+2, sc.add("//[+][,]\n1001,2+32+1,2"));
	}
	
	@Test
	public void addWithMultipleAnyLenghtDelimiters() {
		StringCalculator sc = new StringCalculator();
		assertEquals(2+32+1+2, sc.add("//[***][---]\n1001***2***32---1---2"));
	}	
}
