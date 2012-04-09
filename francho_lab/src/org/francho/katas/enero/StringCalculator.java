/**
 * Ejercicio de 12meses12katas.com
 * 
 * by francho
 * 
 * Solución basada en el uso de expresiones regulares
 * 
 * Twitter: @francho_lab
 * http://francho.org  
 */

package org.francho.katas.enero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Instrucciones de la Kata String Calculator (sacadas de la web:
 * http://www.osherove.com/tdd-kata-1/)
 * ------------------------------------------
 * 
 * Before you start:
 * 
 * - Try not to read ahead. - Do one task at a time. The trick is to learn to
 * work incrementally. - Make sure you only test for correct inputs. there is no
 * need to test for invalid inputs for this kata
 * 
 * String Calculator:
 * 
 * 1) Create a simple String calculator with a method int Add(string numbers) 1.
 * The method can take 0, 1 or 2 numbers, and will return their sum (for an
 * empty string it will return 0) for example “” or “1” or “1,2” 2. Start with
 * the simplest test case of an empty string and move to 1 and two numbers 3.
 * Remember to solve things as simply as possible so that you force yourself to
 * write tests you did not think about 4. Remember to refactor after each
 * passing test 2) Allow the Add method to handle an unknown amount of numbers
 * 3) Allow the Add method to handle new lines between numbers (instead of
 * commas). 1. the following input is ok: “1\n2,3” (will equal 6) 2. the
 * following input is NOT ok: “1,\n” (not need to prove it - just clarifying) 4)
 * Support different delimiters 1. to change a delimiter, the beginning of the
 * string will contain a separate line that looks like this:
 * “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where
 * the default delimiter is ‘;’ . 2. the first line is optional. all existing
 * scenarios should still be supported 5) Calling Add with a negative number
 * will throw an exception “negatives not allowed” - and the negative that was
 * passed.if there are multiple negatives, show all of them in the exception
 * message
 * 
 * ----------------------------------------------------------------------------
 * ------------------------------------------- stop here if you are a beginner.
 * Continue if you can finish the steps so far in less than 30 minutes.
 * ----------
 * --------------------------------------------------------------------
 * -----------------------------------------
 * 
 * 6) Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2 7)
 * Delimiters can be of any length with the following format: “//[delimiter]\n”
 * for example: “//[***]\n1***2***3” should return 6 8) Allow multiple
 * delimiters like this: “//[delim1][delim2]\n” for example “//[*][%]\n1*2%3”
 * should return 6. 9) make sure you can also handle multiple delimiters with
 * length longer than one char
 * 
 * 
 * @author francho
 * 
 */
public class StringCalculator {
	private static final String SINGLE_SEPARATOR = "#";

	public int add(String operation) throws InvalidOperatorException,
			NegativesNotAllowedException {
		int result = 0;

		if ((operation == null) || operation.equals("")) {
			return result;
		}

		// list of separators
		ArrayList<String> separators = new ArrayList<String>();
		// The defaults
		separators.add(",");
		separators.add("\\n");

		// are the separators defined by the user?
		Pattern pattern = Pattern.compile("^//([^\n]+)\n(.*)");
		Matcher matcher = pattern.matcher(operation);

		// Get the separators List
		if (matcher.find()) {
			String userSeparator = matcher.group(1);
			operation = matcher.group(2);
			pattern = Pattern.compile("\\[*([^\\]]+)\\]*");
			matcher = pattern.matcher(userSeparator);

			while (matcher.find()) {
				for (int x = 1; x <= matcher.groupCount(); x++) {
					String regex = regexNormalize(matcher.group(x));
					separators.add(regex);
				}
			}
		}

		// Normalize the separators, this way we only will use one
		Collections.sort(separators, new LengthComparator());

		for (String regex : separators) {
			pattern = Pattern.compile(regex);

			operation = pattern.matcher(operation).replaceAll(SINGLE_SEPARATOR);
		}

		// Check if we have two (or more) separators contiguous
		String regexCheckSeparators = ".*(" + SINGLE_SEPARATOR + "){2,}.*";
		if (Pattern.matches(regexCheckSeparators, operation)) {
			throw new InvalidOperatorException();
		}

		// Sum the operators
		String[] ops = operation.split(SINGLE_SEPARATOR);
		String negativeNumbers = "";

		for (int x = 0; x < ops.length; x++) {
			try {
				int number = Integer.parseInt(ops[x]);
				if (number < 0) {
					negativeNumbers += number + " ";
				} else if (number > 1000) {
					// skip
				} else {
					result += number;
				}
			} catch (NumberFormatException e) {
				// Skip
			}
		}

		// was there negative numbers?
		if (!negativeNumbers.equals("")) {
			throw new NegativesNotAllowedException(negativeNumbers);
		}

		// All fine, return the result
		return result;
	}

	/**
	 * Escape the characters with his hexadecimal value, this way the string will be
	 * use as regex
	 * 
	 * @param group
	 * @return
	 */
	private String regexNormalize(String group) {
		char[] chars = group.toCharArray();

		String regex = "";

		for (int x = 0; x < chars.length; x++) {
			if ((chars[x] != '[') && (chars[x] != ']')) {
				regex += "\\x" + Integer.toHexString(chars[x]);
			}
		}

		return regex;
	}

	/**
	 * Comparator for sort a string list by length from long to sort
	 * 
	 * @author francho
	 * 
	 */
	class LengthComparator implements Comparator<String> {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(String arg0, String arg1) {
			return arg1.length() - arg0.length();
		}
	}
}
