package com.sourcerebels.dojo.kata;

public class StringCalculator {

	public static final String EMPTY_STRING = "";
	public static final String LINE_ENDING = "\\n";
	public static final String DEFAULT_DELIMITER = ",";
	
	public static final int DELIMITER_CHAR_POSITION = 2;
	
	private String delimiter;
	
	public int add(final String aListOfNumbers) {
		
		int total = 0;
		
		for (String number : extractNumbers(aListOfNumbers)) {
		
			if (EMPTY_STRING.equals(number)) {
				return 0;
			}
			total = sum(total, number);
		}
		
		return total;
	}
	
	private int sum(int aNumber, String aStringNumber) {
		
		return aNumber + Integer.parseInt(aStringNumber);
	}

	private String[] extractNumbers(final String aListOfNumbers) {
		
		String listOfNumbersWithoutDelimiter = extractDelimiter(aListOfNumbers);
		return replaceLineEndings(
					listOfNumbersWithoutDelimiter
				).split(delimiter);
	}
	
	private String extractDelimiter(String aListOfNumbers) {
		
		if (aListOfNumbers.startsWith("//")) {
			
			delimiter = parseDelimiter(aListOfNumbers);
			return cutDelimiter(aListOfNumbers);
		}
		delimiter = DEFAULT_DELIMITER;
		return aListOfNumbers;
	}
	
	private String parseDelimiter(String aListOfNumbers) {
		
		return aListOfNumbers.substring(
				DELIMITER_CHAR_POSITION, DELIMITER_CHAR_POSITION + 1);
	}
	
	private String cutDelimiter(String aListOfNumbers) {
		
		return aListOfNumbers.substring(DELIMITER_CHAR_POSITION + 2);
	}
	
	private String replaceLineEndings(String aListOfNumbers) {
		
		return aListOfNumbers.replaceAll(LINE_ENDING, DEFAULT_DELIMITER);
	}
}
 