package com.lebrijo.katas.stringcalculator;

public class StringCalculator {
	Splitter splitter;

	public StringCalculator() {
		splitter = new Splitter();
	}

	public int add(String string) {
		if ("".equals(string))
			return 0;
		String[] values = splitter.split(string);
		checkForNegatives(values);
		return addArray(values);
	}

	private void checkForNegatives(String[] values) {
		String negatives = "";
		for (String value : values) {
			if (value.contains("-"))
				negatives += "," + value;
		}
		if (!"".equals(negatives))
			throw new RuntimeException("Negatives Not Allowed: "
					+ negatives.substring(1));
	}

	private int addArray(String[] values) {
		int sum = 0;
		for (String value : values) {
			sum += toInt(value);
		}
		return sum;
	}

	private int toInt(String value) {
		return Integer.valueOf(value);
	}

}
