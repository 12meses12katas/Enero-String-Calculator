package test;


import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import stringCalculator.StringCalculator;
import stringCalculator.StringCalculator.NegativeException;

public class TestStringCalculator extends TestCase{


	StringCalculator sCalculator;

	@Before
	public void setUp() throws Exception {
		sCalculator = new StringCalculator();
	}

	@After
	public void tearDown() throws Exception {
	}

	public void test_addNoneNumbers() throws NegativeException{

		String input = "";
		int expectedSum = 0;
		assertEquals(expectedSum,sCalculator.Add(input));

	}

	public void test_addOneNumber() throws NegativeException{

		String input = "1";
		int expectedSum = 1;
		assertEquals(expectedSum,sCalculator.Add(input));

	}

	public void test_addTwoNumbers() throws NegativeException{

		String input = "1,3";
		int expectedSum = 4;
		assertEquals(expectedSum,sCalculator.Add(input));
	}

	public void test_addFiveNumbers() throws NegativeException{

		String input = "1,3,6,3,5";
		int expectedSum = 18;
		assertEquals(expectedSum,sCalculator.Add(input));
	}

	public void test_lineAsSeparator() throws NegativeException{

		String input = "1\n3,6\n5";
		int expectedSum = 15;
		assertEquals(expectedSum,sCalculator.Add(input));

	}

	public void test_CustomSeparator() throws NegativeException{

		String input = "//;\n3;6;5;1";
		int expectedSum = 15;
		assertEquals(expectedSum,sCalculator.Add(input));

	}
	
	public void test_OneNegativeException(){

		String input = "//;\n3;6;5;-1";
		try{
			sCalculator.Add(input);	
			fail();
		}catch(NegativeException ex){
			//Test Passed
		}

	}
	
	public void test_MultiplegativeException(){

		String input = "3,6,-5,-1";
		try{
			sCalculator.Add(input);	
			fail();
		}catch(NegativeException ex){
			//Test Passed
		}

	}
	
	public void test_AddMoreThanMax() throws NegativeException{

		String input = "//;\n3;6;5;1003";
		int expectedSum = 14;
		assertEquals(expectedSum,sCalculator.Add(input));

	}
	
	public void test_AnyLenghtDelimiter() throws NegativeException{

		String input = "//[;;;]\n3;;;6;;;5;;;1003";
		int expectedSum = 14;
		assertEquals(expectedSum,sCalculator.Add(input));

	}

	




}
