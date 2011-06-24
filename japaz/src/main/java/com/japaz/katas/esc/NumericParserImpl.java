package com.japaz.katas.esc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericParserImpl implements INumericParser {
	static String DELIMITER_TAG = "^//(.)\n";
	static String LONG_DELIMITER_BLOCK_TAG = "^//(?:\\[([^]]+)])+\n";
	static String LONG_DELIMITER_TAG = "\\[([^]]+)]";
	
	String delimiter = "[,\n]";
	@Override
	public List<Integer> parse(String numbersIn) {
		String numbers = setDelimiter(numbersIn);
		
		List<Integer> result = new ArrayList<Integer>();
		if (numbers.trim().equals("")) {
			return result;
		}
		Pattern p = Pattern.compile(delimiter);
		Matcher m = p.matcher(numbers);
		int before = 0;
		while (m.find()) {
			result.add(Integer.parseInt(numbers.substring(before,m.start())));
			before = m.end();
		}
		if (before < numbers.length()) {
			result.add(Integer.parseInt(numbers.substring(before)));
		}
		
		return result;
	}

	// Refactorize this method. not meet Single Responsability Principle
	private String setDelimiter(String numbers) {
		String result = numbers;
		Pattern p = Pattern.compile(DELIMITER_TAG);
		Matcher m = p.matcher(numbers);
		if (m.find()) {
			if (m.start() == 0) {
				delimiter = m.group(1);
				result = numbers.substring(m.end());
			}
		} else {
			p = Pattern.compile(LONG_DELIMITER_BLOCK_TAG);
			m = p.matcher(numbers);
			if (m.find()) {
				String blockTag = m.group();
				Pattern p2 = Pattern.compile(LONG_DELIMITER_TAG);
				Matcher m2 = p2.matcher(blockTag);
				StringBuilder sb = new StringBuilder();
				int delNum = 0;
				while (m2.find()) {
					if (delNum > 0) {
						sb.append("|");
					}
					delNum++;
					sb.append(m2.group(1));
				}
				delimiter = sb.toString();

				result = numbers.substring(m.end());
			}
		}
		delimiter = delimiter.replace("?", "\\?");
		delimiter = delimiter.replace("*", "\\*");
		delimiter = delimiter.replace("+", "\\+");
		
		return result;
	}
}
