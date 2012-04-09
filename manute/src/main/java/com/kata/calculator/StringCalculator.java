package com.kata.calculator;

public class StringCalculator {

	private static final String DELIMITER = ",|\n";
	private static final String PREFIX = "//";

	public int add(String text) {
		if (text.length() == 0)
			return 0;
		checkNegatives(values(text));
		return sum(values(text));
	}

	private String[] values(String text) {
		return numbers(text).split(delimiter(text));
	}

	private String delimiter(String text) {
		if (text.startsWith(PREFIX))
			return text.substring(2, 3);
		return DELIMITER;
	}

	private String numbers(String text) {
		if (text.startsWith(PREFIX))
			return text.substring(3);
		return text;
	}

	private int sum(String[] values) {
		int total = 0;
		for (String value : values) {
			total += toInt(value);
		}
		return total;
	}

	private void checkNegatives(String[] values) {
		String negatives = "";
		for (String value : values) {
			if (toInt(value) < 0)
				negatives += "," + value;
		}
		if (negatives.length() > 0)
			throw new RuntimeException(negatives.substring(1));
	}

	private int toInt(String value) {
		return Integer.parseInt(value);
	}

}
