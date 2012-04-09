package com.marimon.katas.enero;

/**
 * Hello world!
 */
public class StringCalculator {
    public static void main(final String[] args) {
        System.out.println("Hello World!");
    }

    /**
     * @param values
     *            a delimited (separator is comma or '\n') string with 0, 1 or 2
     *            numbers.
     * @return
     * @throws NegativesNotAllowedException
     */
    public int add(final String values) {

        if (values.length() == 0) {
            return 0;
        } else {
            String separator = "[,\n]";
            String parseable = values;
            NegativesNotAllowedException exception =
                new NegativesNotAllowedException();
            if (values.startsWith("//")) {
                separator = values.substring(2, values.indexOf('\n'));
                if (separator.startsWith("[") && separator.endsWith("]")) {
                    separator =
                        separator.substring(1, separator.length() - 1);
                }
                separator = escape(separator);
                parseable =
                    values.substring(values.indexOf('\n') + 1, values
                        .length());
            }
            String[] split = parseable.split(separator);
            int retVal = 0;
            for (String current : split) {
                Integer candidate = Integer.valueOf(current);
                if (candidate < 0) {
                    exception.addInvalidValue(current);
                } else {
                    if (candidate <= 1000) {
                        retVal += candidate;
                    }
                }
            }
            if (exception.getInvalidValuesSet().size() > 0) {
                throw exception;
            }
            return retVal;
        }
    }

    private String escape(final String separator) {
        return separator.replace("*", "\\*");
    }

}
