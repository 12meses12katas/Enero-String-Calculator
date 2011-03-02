package com.japaz.katas.esc;

import java.util.List;

public class NumericAdderImpl implements INumericAdder {

	@Override
	public int add(List<Integer> numbers) {
		int result=0;;
		
		for (Integer number : numbers) {
			if (number<=1000) {
				result+=number;
			}
		}
		return result;
	}
}
