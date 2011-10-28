package com.lebrijo.katas.stringcalculator;

public class Splitter {
	protected String[] split(String string) {
		String delimiters = extractDelimiters(string);
		String[] values = extractValues(string, delimiters);
		return values;
	}

	private String[] extractValues(String string, String delimiters) {
		if (string.startsWith("//"))
			string = string.substring(4);
		String[] values = string.split(delimiters);
		return values;
	}

	private String extractDelimiters(String string) {
		String delimiters = ",|\n";
		if (string.startsWith("//"))
			delimiters += "|" + string.substring(2, 3);
		return delimiters;
	}
}
