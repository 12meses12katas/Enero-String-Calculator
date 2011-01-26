package com.josmas.katas;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator{

	public int add(String inputToAdd) {
		String trimmedInputToAdd = inputToAdd.trim();
		if (trimmedInputToAdd.equals(""))
			return 0;
		
		String [] numbersToAdd = trimmedInputToAdd.split("[^-0-9]");
		checkForNegatives(numbersToAdd);
		
		return add(numbersToAdd);
	}

	private void checkForNegatives(String[] numbersToAdd) {
		List<String> negativeNumbers = new ArrayList<String>();
		for (String number : numbersToAdd) {
			if ( !number.equals("") && (Integer.parseInt(number) < 0 ) )
				negativeNumbers.add(number);
		}
		
		if (!negativeNumbers.isEmpty())
			throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
	}

	private int add(String[] numbersToAdd) {
		int resultOfAdding = 0;
		for (String number : numbersToAdd) {
			if (!number.equals("") && Integer.parseInt(number) < 1001 )
				resultOfAdding += Integer.parseInt(number);
		}
		return resultOfAdding;
	}
	
}