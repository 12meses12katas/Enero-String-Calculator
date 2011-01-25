package com.josmas.katas;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	public int add(String string) {
		String trimmedInput = string.trim();
		
		if ( trimmedInput.equals("") )
			return 0;
		
		
		//TODO this is way too messy and inflexible - Refactor
		String regExpForSplit = "[\",\"||\"\\n\"]";
		if (trimmedInput.startsWith("//[")){
			int endOfFirstDelimiter = trimmedInput.indexOf("]");
			String firstDelimiter = trimmedInput.substring(3, endOfFirstDelimiter);
			regExpForSplit = "[\",\"||\"\\n\"||" + trimmedInput.substring(3, endOfFirstDelimiter) + "]+";
			
			if ( (trimmedInput.charAt(endOfFirstDelimiter + 1 )) == ('[') ){
				
				trimmedInput = trimmedInput.substring(endOfFirstDelimiter + 1);
				int endOfSecondDelimiter = trimmedInput.indexOf("]");
				regExpForSplit = "[\",\"||\"\\n\"||" + firstDelimiter + "||" + trimmedInput.substring(1, endOfSecondDelimiter) + "]+";
				trimmedInput = trimmedInput.substring(endOfSecondDelimiter + 2);
			}
			else {
				trimmedInput = trimmedInput.substring(endOfFirstDelimiter + 2);
			}
			
		} else if (trimmedInput.startsWith("//")){
			regExpForSplit = "[\",\"||\"\\n\"||\"" + trimmedInput.charAt(2) + "\"]";
			trimmedInput = trimmedInput.substring(4);
		}

		String [] eachNumberInText = trimmedInput.split(regExpForSplit);
				
		return add(eachNumberInText);
	}
	
	private int add(String[] eachNumberInText) {
		int result = 0;
		List<String> negativeNumbers = new ArrayList<String>();
		for (String numberInText : eachNumberInText) {
			int number = Integer.parseInt(numberInText);
			if ( number < 0 ){
				negativeNumbers.add(numberInText);
			}
			if (number < 1001)
				result += number; 
		}
		
		if (!negativeNumbers.isEmpty())
			throw new IllegalArgumentException("No negatives Allowed " + negativeNumbers);
		
		return result;
	}

}
