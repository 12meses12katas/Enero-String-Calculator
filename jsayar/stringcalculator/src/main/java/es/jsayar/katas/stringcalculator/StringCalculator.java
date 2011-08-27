/*
 * This file is part of StringCalculator
 *
 * Copyright (C) 2011 Jesus Miguel Sayar Celestino
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package es.jsayar.katas.stringcalculator;

/**
 * 
 * @author Jesus Miguel Sayar Celestino <xuxoceleste@gmail.com>
 * 
 */
public class StringCalculator {

	public int add(String expression) {
		if (isEmpty(expression))
			return 0;
		else {
			return sum(expression);
		}

	}

	private boolean isEmpty(String expression) {
		return expression.length() == 0;
	}

	private int sum(String expression) {
		String[] numbers = fixerExpression(expression).split(",");
		validateNumbers(numbers);
		return sumNumbers(numbers);
	}

	private void validateNumbers(String[] numbers) {
		StringBuilder negativeNumbers = new StringBuilder();
		for (String number : numbers) {
			if (isNegative(number))
				negativeNumbers.append(number).append(" ");
		}
		if (isEmpty(negativeNumbers))
			throw new IllegalArgumentException("negatives not allowed: "
					+ negativeNumbers);

	}

	private boolean isEmpty(StringBuilder negativeNumbers) {
		return negativeNumbers.length() != 0;
	}

	private boolean isNegative(String number) {
		return number.contains("-");
	}

	private int sumNumbers(String[] numbers) {
		int sum = 0;
		for (String number : numbers) {
			sum += Integer.valueOf(number);
		}
		return sum;
	}

	private String fixerExpression(String expression) {
		char delimiter = '\n';
		if (expression.startsWith("//")) {
			delimiter = expression.charAt(2);
			expression = expression.replace(delimiter, ',');
			return expression.substring(4);
		}
		return expression.replace(delimiter, ',');
	}

}
