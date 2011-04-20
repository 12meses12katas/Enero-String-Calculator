/**
 * Autor: GerardoGomez (Gerardo Gómez-Caminero Ortega)
 * Archivo: StringCalculatorTest.java
 * Creado: 20/04/2011
 * Version: 1.0
 */
package gerardogomez.kata.stringcalculator.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import gerardogomez.kata.stringcalculator.StringCalculator;

/**
 * @author GerardoGomez (Gerardo Gómez-Caminero Ortega)
 *
 */
public class StringCalculatorTest {
	private StringCalculator calculator;
	
	@Before
	public void init(){
		calculator = new StringCalculator();
	}
	
	/*
	 * 1) Create a simple String calculator with a method int Add(string numbers)
		    1. The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
		    2. Start with the simplest test case of an empty string and move to 1 and two numbers
		    3. Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
		    4. Remember to refactor after each passing test
	 */
	
	/**
	 * Ejemplo 1: Si numbers="" obtenemos 0
	 */
	@Test 
	public void AddEmptyString(){
		Assert.assertEquals(0, calculator.add(""));
	}

	/**
	 * Ejemplo 2: Si numbers="2" obtenemos 2
	 */
	@Test 
	public void AddOneNumber(){
		Assert.assertEquals(2, calculator.add("2"));
	}
}
