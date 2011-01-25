package com.farmerdev.katas.stringcalculator;

public class StringCalculator {

	public int add(String stringToParse) {
		int result = 0;
		
		if(stringToParse.contains(",")){
			result = addAString(stringToParse);
		}else if(!stringToParse.equals("")){
			result = Integer.parseInt(stringToParse);
		}
		return result;
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
