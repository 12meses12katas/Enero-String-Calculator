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
     */
    public int add(final String values) {
        if (values.length() == 0) {
            return 0;
        } else {
            String[] split = values.split("[,\n]");
            int retVal = 0;
            for (String current : split) {
                retVal += toNum(current);
            }
            return retVal;
        }
    }

    private Integer toNum(final String lala) {
        return Integer.valueOf(lala);
    }
}
