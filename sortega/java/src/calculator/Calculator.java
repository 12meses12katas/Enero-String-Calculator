package calculator;

import java.util.*;
import java.util.regex.*;

public class Calculator {
    private static final Pattern CUSTOM_DELIMITERS_PATTERN =
	Pattern.compile("//(.*?)\\n(.*)");

    private String delimiterDefinition;
    private String numbers;

    public int add(String input) throws NegativeInput {
        splitInput(input);
        List<String> delimiters = parseDelimiters();

        int sum = 0;
        List<Integer> negatives = new LinkedList<Integer>();
        for(int value: parseNumbers(delimiters)) {
            if (value < 0)
                negatives.add(value);
            else if (value <= 1000)
                sum += value;
        }

        if (negatives.isEmpty())
            return sum;
        else
            throw new NegativeInput(negatives);
    }

    private List<String> parseDelimiters() {
        List<String> delimiters = new LinkedList<String>();

        while(!delimiterDefinition.isEmpty()) {
            if (delimiterDefinition.startsWith("[")) {
                int closeBracket = delimiterDefinition.indexOf("]");
                delimiters.add(delimiterDefinition.substring(1, closeBracket));
                delimiterDefinition = delimiterDefinition.substring(closeBracket + 1);
            } else {
                delimiters.add(delimiterDefinition.substring(0, 1));
                delimiterDefinition = delimiterDefinition.substring(1);
            }
        }

        return delimiters;
    }

    private List<Integer> parseNumbers(List<String> delimiters) {
        List<Integer> result = new LinkedList<Integer>();
        for (String number: numbers.split(separatorRegex(delimiters)))
            if (!number.isEmpty())
                result.add(Integer.parseInt(number));
        return result;
    }

    private String separatorRegex(List<String> delimiters) {
        String regex = "\\n";
        for (String delimiter: delimiters) {
            regex += "|" + Pattern.quote(delimiter);
        }
        return regex;
    }

    private void splitInput(String input) {
        Matcher m = CUSTOM_DELIMITERS_PATTERN.matcher(input);
        if (m.matches()) {
            delimiterDefinition = m.group(1);
            numbers = m.group(2);
        } else {
            delimiterDefinition = ",";
            numbers = input;
        }
    }

    public static class NegativeInput extends RuntimeException {
        public NegativeInput(List<Integer> numbers) {
            super("Negative input: " + formatNumbers(numbers));
        }

        private static String formatNumbers(List<Integer> numbers) {
            String str = "";
            for (int number: numbers)
                str += number + " ";
            return str.trim();
        }
    }
}
