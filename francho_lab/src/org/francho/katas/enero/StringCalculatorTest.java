/**
 * Ejercicio de 12meses12katas.com
 * 
 * by francho
 * 
 * Twitter: @francho_lab
 * http://francho.org  
 */
package org.francho.katas.enero;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Mi primer Junit4 basado en lo que ha hecho eferro
 * 
 * @author francho
 *
 */
public class StringCalculatorTest {
	private StringCalculator stringCalculator;

	@Before
	public void setUp() {
		stringCalculator = new StringCalculator();
	}
	
	@Test
	public void testAddNullStringReturnsCero() throws InvalidOperatorException{
		assertEquals(0, stringCalculator.add(null));
	}
	
	@Test
	public void testAddEmptyString() throws InvalidOperatorException {
		assertEquals(0, stringCalculator.add(""));
	}
	
	@Test
	public void testAddOneNumber() throws InvalidOperatorException {
		assertEquals(1, stringCalculator.add("1"));
	}
	
	@Test
	public void testAddTwoNumbers() throws InvalidOperatorException {
		assertEquals(3, stringCalculator.add("1,2"));
	}
	
	@Test
	public void testAddUnknownAmountOfNumbers() throws InvalidOperatorException {
		assertEquals(25, stringCalculator.add("1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10"));
	}
	
	@Test
	public void testAddNewLineDelimiter() throws InvalidOperatorException {
		assertEquals(6, stringCalculator.add("1\n2,3"));
	}
	
	@Test (expected=InvalidOperatorException.class) public void testInvalidOperator() throws InvalidOperatorException {
		stringCalculator.add("1,\n"); // Should throw InvalidOperatorException
	}
	
	@Test
	public void testUserDefinedDelimiters() throws InvalidOperatorException {
		assertEquals(3,stringCalculator.add("//;\n1;2"));
	}
	
	@Test (expected=NegativesNotAllowedException.class) public void testNegativesNotAllowed() throws InvalidOperatorException {
		stringCalculator.add("1,-2,-3"); // Should throw NegativesNotAllowedException
	}
	
	@Test
	public void testAddOver1000() throws InvalidOperatorException {
		assertEquals(2, stringCalculator.add("1002,2"));
	}
	
	@Test
	public void testAnyLengthUserDelimiter() throws InvalidOperatorException {
	    assertEquals(6, stringCalculator.add("//[***]\n1***2***3"));
	    assertEquals(6, stringCalculator.add("//[,,,]\n1,,,2,,,3"));
	}
	
	@Test
	public void testAnyLengthMultipleUserDelimiter() throws InvalidOperatorException {
	    assertEquals(6, stringCalculator.add("//[***][,,,]\n1***2,,,3"));
	    assertEquals(6, stringCalculator.add("//[*][%]\n1*2%3"));
	    assertEquals(6, stringCalculator.add("//[..][,,,]\n1..2,,,3"));
	}
	
}
