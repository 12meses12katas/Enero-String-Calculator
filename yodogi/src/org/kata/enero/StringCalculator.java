package org.kata.enero;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringCalculator {

	private List<String> delimiters;
	
	public StringCalculator() {
		delimiters = new ArrayList<String>();
	}
	
	public int add(String numbers) throws PatternSyntaxException {
		int result = 0;
		String[] operands = numbers.split("\n");
		if (operands.length == 0) return result;
		
		// Verificamos si se define el delimitador de operaciones
		int indx = parseDelimiter(operands[0].replaceAll("\\s+", ""));

		if (indx == 0)
			delimiters.add(",");
		
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
	
	private boolean parseSimpleDelimiter(String candidateDelimiter) { // Parseo delimitador simple
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile("//([^0-9\\-\\[])");
		matcher = pattern.matcher(candidateDelimiter);
		if (matcher.find()) {
			delimiters.add(matcher.group(1));
			return true;
		}
		
		return false;
	}
	
	private boolean parseComplexDelimiter(String candidate) { // Parseo delimitador complejo
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile("(\\/\\/)(\\[(.*?)\\])");
		matcher = pattern.matcher(candidate);
		if (matcher.find()) {
			delimiters.add(matcher.group(3));
			parseComplexDelimiter(candidate.substring(0, matcher.start(2)) + candidate.substring(matcher.end(2)));
			return true;
		}

		return false;
	}

	private int parseDelimiter(String candidateDelimiter) {

		delimiters.clear();

		if (parseSimpleDelimiter(candidateDelimiter))
			return 1;
		else if (parseComplexDelimiter(candidateDelimiter))
			return 1;
		else
			return 0;
	}
	
	private int calcutate(String expresion) {
		int result = 0;
		
		// Valida operadores
		int numOperadores = 0;
		String[] operadores = expresion.split("[\\d-]");
		for (String operador: operadores)
			if (!operador.equals(""))
				if (delimiters.contains(operador))
					numOperadores++;
				else
					throw new PatternSyntaxException("the follow delimiter not defined", operador, expresion.indexOf(operador));

		// Determino expresión
		String patternStr = "";
		if (delimiters.size() > 1)
			for (Object delimiter: delimiters.toArray())
				patternStr += "[" + delimiter + "]";
		else
			patternStr = delimiters.get(0);
		
		// Determino operandos
		int numOperandos = 0;
		patternStr = "[" + patternStr + "]";
		String[] operandos = expresion.split(patternStr);
		for (String operando : operandos)
			if (!operando.equals("")) numOperandos++;

		// Valido la correspondencia entre operandos y operadores (cadena vacia no se procesa)
		if ((numOperandos > 0) && (numOperandos - 1 != numOperadores))
			throw new PatternSyntaxException("the following input is NOT ok", expresion, 0);
		
		for (String numStr : operandos) {
			if (numStr.equals("")) continue;
			try {
				
				int num = Integer.parseInt(numStr);
				
				if (num < 0)
					throw new PatternSyntaxException("negatives not allowed", expresion, expresion.indexOf(numStr));
				else if (num <= 1000) // the numbers bigger than 1000 should be ignored
					result += num;
				
			} catch (NumberFormatException ex) {
				// On exceptions numStr equal zero
			}
		}
		
		return result;
		
	}

}
