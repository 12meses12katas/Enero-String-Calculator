package sorac.co.uk.calculator;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class StringCalculator {

    public Integer add(String string) {

        Preconditions.checkNotNull(string, "The input should not be null");

        if (string.isEmpty()) {
            return 0;
        }

        CalculatorInput calculatorInput = getInput(string).initialise();

        List<Integer> values = calculatorInput.getValues();

        Preconditions.checkArgument(calculatorInput.getNegatives().isEmpty(),
                "The input should not contain negatives, but has %s",
                StringUtils.join(calculatorInput.getNegatives(), ","));

        return sumMembers(values);

    }

    private CalculatorInput getInput(String string) {
        String[] spplited = string.split("//.\n");
        if (spplited.length > 1) {
            return new CalculatorInput(spplited[1], getDelimiter(string));
        } else {
            return new CalculatorInput(string);
        }
    }

    private Integer sumMembers(List<Integer> members) {
        Integer sum = 0;

        for (Integer member : members) {
            if (member <= 1000) {
                sum += member;
            }
        }

        return sum;
    }
    
    private String getDelimiter(String string) {
        return new Character(string.charAt(2)).toString();
    }

    private class CalculatorInput {
        private final String delimiter;

        private final String value;

        private List<Integer> values;

        private List<Integer> negatives;

        public CalculatorInput(String value, String delimiter) {
            this.delimiter = delimiter;
            this.value = value;
        }

        public CalculatorInput(String value) {
            this(value, ",");
        }

        public CalculatorInput initialise() {
            List<String> spplited = Arrays.asList(value.split(delimiter + "|\n"));
            values = Lists.newArrayList(Iterables.transform(spplited, new IntegerConverter()));
            negatives = Lists.newArrayList(Iterables.filter(values, new NegativePredicate()));
            return this;
        }

        public List<Integer> getValues() {
            return values;
        }

        public List<Integer> getNegatives() {
            return negatives;
        }

    }

}
