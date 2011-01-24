package com.josmas.katas;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	public int add(final String numbers) {
		String numberSequence = numbers.trim();

		if (numberSequence.equals(""))
			return 0;

		String separatorRegexp = "[\",\"||\"\n\"]";
		if ( numberSequence.startsWith("//") ){
			separatorRegexp = "[\",\"||\"\n\"||\"" + numberSequence.charAt(2) + "\"]";
			numberSequence = numberSequence.substring(4);
		}
		
		return transformNumbers(numberSequence.split(separatorRegexp));
	}

	private int transformNumbers(String[] separateNumbers){
		int result = 0;
		List<String> negativesForReport = new ArrayList<String>();
		for (int i = 0; i < separateNumbers.length; i++){
			int number = Integer.parseInt(separateNumbers[i]);
			if ( number < 0 )
				negativesForReport.add(separateNumbers[i]);
			result += number;
		}
		
		if (!negativesForReport.isEmpty())
			throw new IllegalArgumentException("Negatives not allowed: " + negativesForReport);
		
		return result;
	}

}
