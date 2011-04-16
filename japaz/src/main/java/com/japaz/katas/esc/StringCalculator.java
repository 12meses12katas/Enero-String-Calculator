package com.japaz.katas.esc;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	INumericParser parser;
	INumericAdder adder;
	
	public StringCalculator(INumericParser numericParser,
							INumericAdder numericAdder) {
		parser = numericParser;
		adder = numericAdder;
		
	}
	public int add(String numbers) throws Exception {
		int result;
		List<Integer> numbers2 = parser.parse(numbers);
		List<Integer> negatives = getNegatives(numbers2);
		if (negatives.size()>0) {
			StringBuffer sb = new StringBuffer("negatives not allowed:");
			for (Integer negative : negatives) {
				sb.append(" ").append(negative);
			}
			throw new Exception(sb.toString());
		}
		
		result = adder.add(numbers2);
		
		return result;
	}
	
	private List<Integer> getNegatives(List<Integer> numbers) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer number : numbers) {
			if (number < 0) {
				result.add(number);
			}
		}
		return result;
	}
}
