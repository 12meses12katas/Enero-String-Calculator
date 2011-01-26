package com.farmerdev.katas.stringcalculator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.farmerdev.katas.stringcalculator.exceptions.NumberException;

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
	
	@Test
	public void when_takes_one_number_then_should_return_the_same_number(){
		String [] valuesTaken={"1","2","3","10","100"};
		int [] resultExpected ={1,2,3,10,100};
		 
		for(int i=0;i<valuesTaken.length;i++){		
			int result = stringCalculator.add(valuesTaken[i]);
			assertThat(result, is(resultExpected[i]));
		}

	}
	
	@Test
	public void when_takes_two_numbers_then_should_return_their_sum(){
		String [] valuesTaken={"1,2","2,10","3,30","10,100","20,100"};
		int [] resultExpected ={3,12,33,110,120};
		 
		for(int i=0;i<valuesTaken.length;i++){		
			int result = stringCalculator.add(valuesTaken[i]);
			assertThat(result, is(resultExpected[i]));
		}
	}
	
	@Test
	public void when_unknown_amount_of_numbers_are_taken_then_should_return_their_sum(){
		String [] valuesTaken={"1,2,3,10","2,10,22","3,30","10,100,9,1,4,23","20,100,500,2"};
		int [] resultExpected ={16,34,33,147,622};
		for(int i=0;i<valuesTaken.length;i++){		
			int result = stringCalculator.add(valuesTaken[i]);
			assertThat(result, is(resultExpected[i]));
		}
	}
	
	@Test
	public void when_new_line_delimiter_is_added_then_should_return_their_sum(){
		String [] valuesTaken={"1\n2","2\n10,22","10,100,9\n1,4,23","20,100,500\n2"};
		int [] resultExpected ={3,34,147,622};
		for(int i=0;i<valuesTaken.length;i++){		
			int result = stringCalculator.add(valuesTaken[i]);
			assertThat(result, is(resultExpected[i]));
		}
	}
	
	@Test
	public void when_new_delimiter_is_supported_then_should_take_as_new_delimiter_and_sum_the_numbers(){
		String [] valuesTaken={"//;\n2;3","//+\n10+22","//-\n10-100-9-1-4-23"};
		int [] resultExpected ={5,32,147};
		for(int i=0;i<valuesTaken.length;i++){		
			int result = stringCalculator.add(valuesTaken[i]);
			assertThat(result, is(resultExpected[i]));
		}
	}
	@Test
	public void when_new_delimiter_is_supported_without_new_line_then_should_take_as_new_delimiter_and_sum_the_numbers(){
		String [] valuesTaken={"//;2;3","//+10+22","//-10-100-9-1-4-23"};
		int [] resultExpected ={5,32,147};
		for(int i=0;i<valuesTaken.length;i++){		
			int result = stringCalculator.add(valuesTaken[i]);
			assertThat(result, is(resultExpected[i]));
		}
	}
	
	@Test(expected=com.farmerdev.katas.stringcalculator.exceptions.NumberException.class)
	public void when_a_negative_number_is_added_then_should_throw_an_exception(){
		
		stringCalculator.add("2,-3");
		
	}
	
	@Test
	public void when_a_negative_number_is_added_then_should_throw_an_exception_with_a_message(){
		String message = "Negative numbers are not allowed [-3 -5 ]";
		try{
			
			stringCalculator.add("2,-3,-5");
			fail("Must throw an Exception");
			
		}catch(NumberException ex){
			assertThat(message,is(ex.getMessage()));
		}
	}
	
	
	
	
	
	
	
}
