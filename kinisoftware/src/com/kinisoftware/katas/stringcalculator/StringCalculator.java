package com.kinisoftware.katas.stringcalculator;

public class StringCalculator {

	public static Integer add(String string) {
		if (isEmpty(string))
			return 0;
		if (hasOnlyOneChar(string))
			return Integer.valueOf(string);		
		return sum(string);
	}

	private static Integer sum(String string) {
		String[] numbers = extractNumbersFrom(string);		
		validateNumbers(numbers);		
		return sumNumbers(numbers);
	}

	private static void validateNumbers(String[] numbers) {
		StringBuilder negativeNumbers = new StringBuilder();
		for (String number : numbers) {
			if (isNegative(number))
				negativeNumbers.append(number).append(" ");
		}
		if (notEmpty(negativeNumbers))
			throw new IllegalArgumentException("not allowed negative numbers: " + negativeNumbers);
	}

	private static boolean notEmpty(StringBuilder negativeNumbers) {
		return negativeNumbers.length() != 0;
	}

	private static boolean isNegative(String number) {
		return number.contains("-");
	}

	private static Integer sumNumbers(String[] numbers) {
		int sum = 0;
		for (String number : numbers) 
			sum += Integer.valueOf(number);
		return sum;
	}

	private static String[] extractNumbersFrom(String string) {
		String  normalizedString = normalizeString(string);
		String[] numbers = normalizedString.split(",");
		return numbers;
	}

	private static String normalizeString(String string) {
		if (string.startsWith("//")) {
			char delimiter = string.charAt(2);
			string = string.substring(3);
			string = string.replace(delimiter, ',');
		}
		return string;
	}

	private static boolean hasOnlyOneChar(String string) {
		return string.length() == 1;
	}

	private static boolean isEmpty(String string) {
		return string.isEmpty();
	}

}
