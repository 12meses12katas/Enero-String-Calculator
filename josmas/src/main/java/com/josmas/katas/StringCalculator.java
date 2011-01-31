package com.josmas.katas;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator{
	
	public int add(String inputString){
		String trimmedInput = inputString.trim();
		if (trimmedInput.equals(""))
			return 0;
		
		int result = 0;
		String [] inputNumbers = trimmedInput.split("[^-0-9]");
		checkForNegatives(inputNumbers);
		for (String number : inputNumbers) {
			if ( !number.equals("") && Integer.parseInt(number) < 1001)
				result += Integer.parseInt(number);
		}
		
		return result;
	}

	private void checkForNegatives(String[] inputNumbers) {
		List<String> negativesFound = new ArrayList<String>();
		for (String number : inputNumbers) {
			if ( !number.equals("") && Integer.parseInt(number) < 0 )
				negativesFound.add(number);
		}
		
		if (!negativesFound.isEmpty())
			throw new IllegalArgumentException("Negatives found: " + negativesFound);
		
	}
}