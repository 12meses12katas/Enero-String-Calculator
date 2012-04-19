package sorac.co.uk.calculator;

import com.google.common.base.Function;

public class IntegerConverter implements Function<String, Integer>{

    public Integer apply(String string) {
        return Integer.valueOf(string);
    }

}
