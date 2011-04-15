package kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

	public int add(String inputStr) {
		String firstLine = getLineDelimiters(inputStr);
		String textWithNumbers = getStringWithoutDelimiterLine(inputStr);
		List<String> delimiters = getDelimiters(firstLine);

		List<Integer> numbers = getAllowedNumbers(getNumbers(textWithNumbers,
				delimiters));
		checkNegativeNumbers(numbers);
		return getSum(numbers);
	}

	private final static String DELIMITER_ALWAYS_NEWLINE = "\n";
	private final static String DELIMITER_ALWAYS_COMMA = ",";
	private final static String[] DELIMITERS_FIRSTLINE = { "][", "[", "]" };

	private static List<String> getDelimiters(String delimiterSequence) {
		List<String> delimiters = new ArrayList<String>();
		delimiters.add(DELIMITER_ALWAYS_NEWLINE);
		delimiters.add(DELIMITER_ALWAYS_COMMA);
		delimiters.addAll(splitWithDelimiters(delimiterSequence,
				DELIMITERS_FIRSTLINE));
		return delimiters;
	}

	private static String getLineDelimiters(String str) {
		int posNewline = str.indexOf("]\n");
		if (posNewline == -1 || !str.startsWith("//[")) {
			return "";
		} else {
			return str.substring(2, posNewline + 1);
		}
	}

	private static String getStringWithoutDelimiterLine(String str) {
		int posNewline = str.indexOf("]\n");
		if (posNewline == -1) {
			return str;
		} else {
			return str.substring(posNewline + 2);
		}
	}

	private static int getSum(List<Integer> numbers) {
		int sum = 0;
		for (int num : numbers) {
			sum += num;
		}
		return sum;
	}

	private static List<Integer> getAllowedNumbers(List<Integer> numbers) {
		List<Integer> allowed = new ArrayList<Integer>();
		for (int num : numbers) {
			if (num <= 1000) {
				allowed.add(num);
			}
		}
		return allowed;
	}

	private static void checkNegativeNumbers(List<Integer> numbers)
			throws IllegalArgumentException {
		List<Integer> negatives = new ArrayList<Integer>();
		for (int num : numbers) {
			if (num < 0) {
				negatives.add(num);
			}
		}
		if (negatives.size() > 0) {
			throw new IllegalArgumentException("no se permiten negativos: "
					+ negatives.toString());
		}
	}

	private static List<Integer> getNumbers(String str, List<String> delimiters) {
		List<Integer> list = new ArrayList<Integer>();
		for (String strnum : splitWithDelimiters(str, delimiters)) {
			list.add(Integer.parseInt(strnum));
		}
		return list;
	}

	private static List<String> splitWithDelimiters(String text,
			List<String> delimiters) {
		List<String> list = new ArrayList<String>();
		for (String elm : text.split(getPatternForDelimiters(delimiters))) {
			if (elm.length() > 0) {
				list.add(elm);
			}
		}
		return list;
	}

	private static List<String> splitWithDelimiters(String text,
			String[] delimiters) {
		return splitWithDelimiters(text, Arrays.asList(delimiters));
	}

	private static String getPatternForDelimiters(List<String> delimiters) {
		StringBuilder builder = new StringBuilder();
		boolean afterFirst = false;
		for (String delimiter : delimiters) {
			if (afterFirst) {
				builder.append('|');
			} else {
				afterFirst = true;
			}
			builder.append(Pattern.quote(delimiter));
		}
		return builder.toString();
	}
}
