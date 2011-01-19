package com.josmas.katas;

public class StringCalculator {

	public int add(final String numbers) {
		String numberSequence = numbers;

		if (numberSequence.equals(""))
			return 0;

		String [] separateNumbers = numberSequence.split(",");
		int result;

		if (separateNumbers.length == 1)
			result = Integer.parseInt(separateNumbers[0]);
		else if (separateNumbers.length == 2)
			result = transformNumber(separateNumbers);
		else
			throw new IllegalArgumentException("Passing more than 2 numbers");
			
		return result;
	}

	private int transformNumber(String[] separateNumbers){
		int result = 0;
		for (int i = 0; i < separateNumbers.length; i++)
			result += Integer.parseInt(separateNumbers[i]);

		return result;
	}

}
