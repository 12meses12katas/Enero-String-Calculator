package com.farmerdev.katas.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.farmerdev.katas.stringcalculator.exceptions.NumberException;

public class StringCalculator {

	public int add(String stringToParse) {
		int result = 0;
		String delimiter = ",";
		
		if(stringToParse.contains("//")){
			delimiter = findDelimiterAssigned(stringToParse);
			stringToParse = selectNewStringToParse(stringToParse,delimiter);
		}else if(stringToParse.contains("\n")){
			delimiter = "\n";
		}
		
		stringToParse = replaceDelimiterWithComma(stringToParse, delimiter);
		
		if(stringToParse.contains("-")){
			String message = composeErrorMessage(stringToParse);
			throw new NumberException("Negative numbers are not allowed ["+message+"]");
		}
		
		if(stringToParse.contains(",")){
			result = addAString(stringToParse);
		}else if(!stringToParse.equals("")){
			result = Integer.parseInt(stringToParse);
		}
		return result;
	}

	private String composeErrorMessage(String stringToParse) {
		StringBuilder message = new StringBuilder();
		String [] numbersToAdd = stringToParse.split(",");
		for(String aux:numbersToAdd){
			int intAux= Integer.parseInt(aux);
			if(intAux<0)
				message.append(intAux+" ");
		}
		
		return message.toString();
	}

	private String replaceDelimiterWithComma(String stringToParse,
			String delimiter) {
		if(!",".equals(delimiter))
			stringToParse = stringToParse.replace(delimiter, ",");
		return stringToParse;
	}

	private String selectNewStringToParse(String stringToParse,String delimiter) {
		if(stringToParse.contains("\n")){
			stringToParse =stringToParse.substring(stringToParse.indexOf("\n")+1);		}
		else
			stringToParse =stringToParse.substring(stringToParse.indexOf(delimiter)+1);
		return stringToParse;
	}

	private String findDelimiterAssigned(String stringToParse) {
		int initPosition = findStartPosition(stringToParse);
		int endPosition = findEndPosition(stringToParse);
		String delimiter = stringToParse.substring(initPosition,endPosition); 
		return delimiter;
	}

	private int findStartPosition(String stringToParse) {
		int initPosition = stringToParse.indexOf("//")+2;
		return initPosition;
	}

	private int findEndPosition(String stringToParse) {
		int endPosition = 0;
		if(stringToParse.contains("\n"))
				endPosition = stringToParse.indexOf("\n");
		else{
			Pattern pattern = Pattern.compile("(//)(\\W*)(\\d*)(.*)");
			Matcher matcher = pattern.matcher(stringToParse);
			if(matcher.find()){
				endPosition = matcher.start(3);
			}
		}
		return endPosition;
	}

	private int addAString(String stringToParse) {
		int result = 0;
		String [] numbersToAdd = stringToParse.split(",");
		for(String aux:numbersToAdd){
			result += Integer.parseInt(aux);
		}
		return result;
	}

}
