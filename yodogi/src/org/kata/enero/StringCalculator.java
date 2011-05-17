package org.kata.enero;

public class StringCalculator {

	String numbers;
	String delimiter;
	
	
	public StringCalculator() {
		numbers = "";
		delimiter = ",";
	}
	
	public StringCalculator(String numbers) {
		setNumbers(numbers);
	}
	
	public int getValue() {
		int result = 0;
		String[] operands = numbers.split("\n");
		if (operands.length == 0) return result;
		
		int indx = 0;
		if (operands[0].indexOf("//") == 0) { // Initializate StringCalculator
			indx++;
		}
		
		while (indx < operands.length) {
			int cal = calcutate(operands[indx]);
			result += cal;
			indx++;
		}
		
		/*
//		String patternStr = "[, ]+"; // (and|or)*[, \n]*";
		
		String patternStr = "\n"; // (and|or)*[, \n]*";
		for (String operands : ) {
			result += calcutate(operands);
		}
		*/
		
		return result;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	private int calcutate(String operands) {
		int result = 0;
		String patternStr = "[" + delimiter + " ]";

		System.out.print("Sumando elementos(" + operands.split(patternStr).length + "):");
		for (String numStr : operands.split(patternStr))
			try {
				System.out.print("(" + numStr + ")");
				result += Integer.parseInt(numStr);
			} catch (NumberFormatException ex) {
				// On exceptions numStr equal zero
			}
		
		System.out.println(".");
		return result;
		
	}

}
