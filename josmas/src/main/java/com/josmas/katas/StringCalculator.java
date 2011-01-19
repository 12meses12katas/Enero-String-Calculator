package com.josmas.katas;

public class StringCalculator {

	public int add(final String numbers) {
		String numberSequence = numbers.trim();

		if (numberSequence.equals(""))
			return 0;

		String [] separateNumbers = numberSequence.split(",");
		int result = 0;

		if (separateNumbers.length >= 1)
			result = transformNumber(separateNumbers);			

		return result;
	}

	private int transformNumber(String[] separateNumbers){
		int result = 0;
		for (int i = 0; i < separateNumbers.length; i++)
			result += Integer.parseInt(separateNumbers[i]);

		return result;
	}

}
