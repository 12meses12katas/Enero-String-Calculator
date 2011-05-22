package com.stringcalculator.src;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator
{
	private static final int MAX_VALUE = 1000;
	private static final String NEGATIVE_PREFIX = "-";
	private static final String NEGATIVE_ERROR_MESSAGE = "negatives not allowed";
	private static final String OR = "|";
	private static final String NEW_LINE = "\\n";
	private static final String ASSIGN_DELIMITER = "//";
	private static final String DEFAULT_DELIMITER = ",".concat(OR + NEW_LINE).concat(OR + ASSIGN_DELIMITER).concat(OR + "\\[").concat(OR + "\\]");

	public static int add(String input) throws Exception
	{
		int sum = 0;

		if (inputHasOperands(input))
		{
			List<String> operands = getNonEmptyOperands(input.split(getDefaultDelimiter(input)));

			throwExceptionIfExistNegativeOperands(operands);
			operands = getOperandsLessThanThousand(operands);
			for (String operand : operands)
				sum += Integer.parseInt(operand);
		}

		return sum;
	}

	private static List<String> getOperandsLessThanThousand(List<String> operands)
	{
		List<String> operandsLessThanThousand = new ArrayList<String>();
		
		for (String operand:operands)
			if (Integer.parseInt(operand) <= MAX_VALUE)
				operandsLessThanThousand.add(operand);
		
		return operandsLessThanThousand;
	}

	private static void throwExceptionIfExistNegativeOperands(List<String> operands) throws Exception
	{
		List<String> negativeOperands = getNegativeOperands(operands);

		if (negativeOperands.size() > 0)
		{
			String errorMessage = NEGATIVE_ERROR_MESSAGE;

			for (String negativeOperand : negativeOperands)
				errorMessage = errorMessage.concat(" " + negativeOperand);

			throw new Exception(errorMessage);
		}
	}

	private static List<String> getNegativeOperands(List<String> operands)
	{
		List<String> negativeOperands = new ArrayList<String>();

		for (String operand : operands)
			if (operand.startsWith(NEGATIVE_PREFIX))
				negativeOperands.add(operand);

		return negativeOperands;
	}

	private static List<String> getNonEmptyOperands(String[] operands)
	{
		List<String> nonEmptyOperands = new ArrayList<String>();

		for (String input : operands)
			if (inputHasOperands(input))
				nonEmptyOperands.add(input);

		return nonEmptyOperands;
	}

	private static String getDefaultDelimiter(String input)
	{
		String defaultDelimiter = DEFAULT_DELIMITER;

		if (input.startsWith(ASSIGN_DELIMITER))
		{
			String[] delimiters = input.split(NEW_LINE)[0].substring(ASSIGN_DELIMITER.length()).split("\\[|\\]");
			for (String delimiter : delimiters)
				if (delimiter.length() > 0)
				defaultDelimiter = defaultDelimiter.concat(OR + delimiter);
		}

		return escapeMetaRegex(defaultDelimiter);
	}

	private static String escapeMetaRegex(String regex)
	{
		return regex.replace("*", "\\*").replace("+", "\\+").replace("(", "\\(").replace(")", "\\)");
	}

	private static boolean inputHasOperands(String input)
	{
		return input.length() != 0;
	}

}
