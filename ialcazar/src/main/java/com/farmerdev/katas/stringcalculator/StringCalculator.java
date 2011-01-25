package com.farmerdev.katas.stringcalculator;

public class StringCalculator {

	public int add(String stringToParse) {
		int result = 0;
		if("".equals(stringToParse))
			result = 0;
		else if(stringToParse.contains(",")){
			result = 0;
			String [] numbersToAdd = stringToParse.split(",");
			for(String aux:numbersToAdd){
				result += Integer.parseInt(aux);
			}
			
		}else
			result = Integer.parseInt(stringToParse);
		
		return result;
	}

}
