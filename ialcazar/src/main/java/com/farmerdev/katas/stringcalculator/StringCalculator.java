package com.farmerdev.katas.stringcalculator;

public class StringCalculator {

	public int add(String stringToParse) {
		if("".equals(stringToParse))
			return 0;
		else
			return Integer.parseInt(stringToParse);
	}

}
