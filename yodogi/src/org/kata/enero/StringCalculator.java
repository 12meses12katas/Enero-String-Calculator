package org.kata.enero;

import java.util.regex.PatternSyntaxException;

public class StringCalculator {

	char delimiter;
	
	public StringCalculator() {
		delimiter = ',';
	}
	
	public StringCalculator(char delimiter) {
		setDelimiter(delimiter);
	}
	
	public int add(String numbers) throws PatternSyntaxException {
		int result = 0;
		String[] operands = numbers.split("\n");
		if (operands.length == 0) return result;
		
		int indx = 0;
		String defDelimiter = operands[indx].replaceAll("\\s+", "");
		if ((defDelimiter.indexOf("//") == 0)  && (defDelimiter.length() == 3)) { // Initializate StringCalculator
			delimiter = defDelimiter.charAt(2);
			indx++;
		}
		
		while (indx < operands.length) {
			int cal = calcutate(operands[indx].replaceAll("\\s+", ""));
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

	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}
	
	private int calcutate(String operands) {
		int result = 0;
		String patternStr = "[" + delimiter + "]";

		String[] operators = operands.split(patternStr);
		
		if (operators.length == 1 && operands.contains("" + delimiter))
			throw new PatternSyntaxException("the following input is NOT ok", operands, operands.indexOf(delimiter) + 1);
		
		for (String numStr : operands.split(patternStr))
			try {
				
				int num = Integer.parseInt(numStr);
				
				if (num < 0)
					throw new PatternSyntaxException("negatives not allowed", operands, operands.indexOf(delimiter) + 1);
				
				result += num;
				
			} catch (NumberFormatException ex) {
				// On exceptions numStr equal zero
			}
		
		return result;
		
	}

}
