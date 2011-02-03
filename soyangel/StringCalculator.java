import java.util.regex.Pattern;

public class StringCalculator
{

	private static final String NEGATIVES_MESSAGE = "Negatives not allowed";
	private static final String NUMBER_SEPARATOR = ",|\n";
	private static final String PREFIX_CUSTOM_DELIMITER = "//";

	public static int calculate(String numbers) throws Exception
	{
		int[] operands = extractNumbers(numbers);
		return addNumbers(operands);
	}

	private static int[] extractNumbers(String numbers)
	{
		String[] operands = splitArray(numbers);
		return stringArrayToIntArray(operands);
	}

	private static String[] splitArray(String numbers)
	{
		if (numbers.equals(""))
			return new String[0];

		String delimiter = getDelimiter(numbers);
		numbers = removeDelimiterDefinition(numbers);
		return numbers.split(delimiter);
	}

	private static String removeDelimiterDefinition(String numbers)
	{
		if (numbers.startsWith(PREFIX_CUSTOM_DELIMITER))
			return numbers.replaceFirst(PREFIX_CUSTOM_DELIMITER + ".\n", "");
		else
			return numbers;
	}

	private static String getDelimiter(String numbers)
	{
		if (numbers.startsWith(PREFIX_CUSTOM_DELIMITER))
			return extractDeclaredDelimiter(numbers);
		else
			return NUMBER_SEPARATOR;
	}

	private static String extractDeclaredDelimiter(String numbers)
	{
		String delimiter = numbers.split("//|\n")[1];
		return Pattern.quote(delimiter);
	}

	private static int addNumbers(int[] numbers) throws Exception
	{
		int sum = 0;
		String negativeNumbers = "";
		for (int number : numbers)
		{
			if (number < 0)
				negativeNumbers += " " + number;
			else
				sum += number;
		}

		if (negativeNumbers.isEmpty())
			return sum;
		else
			throw new Exception(NEGATIVES_MESSAGE + negativeNumbers);
	}

	private static int[] stringArrayToIntArray(String[] operands)
	{
		int[] result = new int[operands.length];

		for (int i = 0; i < operands.length; i++)
		{
			result[i] = Integer.parseInt(operands[i]);
		}

		return result;
	}

}
