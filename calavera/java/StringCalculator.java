import java.util.regex.*;

public class StringCalculator {
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("\\/\\/(.+)\\n");

    public int calculate(String sequence) {
        if (sequence == null || sequence.length() == 0)
            return 0;

        String delimiter = scanDelimiter(sequence);
        sequence = cleanSequence(sequence);

        String[] numbers = sequence.split(delimiter);

        int result = 0;
        boolean negatives = false;
        for (String number : numbers) {
            int value = Integer.parseInt(number);

            if (value < 0)
                negatives = true;
            else if (value <= 1000)
                result += value;
        }
        if (negatives)
            throw new IllegalArgumentException("negatives not allowed: " + sequence);

        return result;
    }

    String scanDelimiter(String sequence) {
        Matcher matcher = DELIMITER_PATTERN.matcher(sequence);
        if (!matcher.find()) {
            return ",|\n";
        }

        Pattern pattern = Pattern.compile("(?<=\\[)[^\\]]+(?=\\])");
        Matcher bracketMatcher = pattern.matcher(matcher.group(1));

        StringBuilder delimiters = new StringBuilder();
        while (bracketMatcher.find()) {
            delimiters.append(bracketMatcher.group(0)).append("|");
        }

        if (delimiters.length() == 0) {
            delimiters.append(matcher.group(1));
        } else {
            delimiters.setLength(delimiters.length() - 1);
        }

        return delimiters.toString();
    }

    String cleanSequence(String sequence) {
        Matcher matcher = DELIMITER_PATTERN.matcher(sequence);
        if (matcher.find()) {
            return sequence.replace(matcher.group(0), "");
        }
        return sequence;
    }
}
