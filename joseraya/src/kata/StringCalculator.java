package kata;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StringCalculator {
	
	public int add(String numbers) {
		if (numbers.isEmpty()) {
			return 0;
		} else {
			return addNumbers(numbers);			
		}
	}

	private int addNumbers(String input) {
		int result = 0;
		List<String> numberStrings = getNumbersFromString(input);
		checkForNegatives(numberStrings);
		for(String current: numberStrings) {
			result +=  Integer.valueOf(current);;
		}
		return result;
	}

	private void checkForNegatives(List<String> numberStrings) {
		Set<Integer> negativesFound = new HashSet<Integer>();
		for(String current: numberStrings) {
			int number = Integer.valueOf(current);
			if (number < 0) {
				negativesFound.add(number);
			}
		}
		if (negativesFound.size() > 0) {
			StringBuilder message = new StringBuilder("Negatives not allowed. Found: ");
			for(Integer negative: negativesFound) {
				message.append(negative).append(" ");
			}
			throw new IllegalArgumentException(message.toString());
		}
	}

	private List<String> getNumbersFromString(String numbers) {
		List<String> result = new LinkedList<String>();
		for(String s : numbers.split("[^-0-9]")) {
			if(!s.isEmpty()) {
				result.add(s);
			}
		}
		return result;
	}
}
