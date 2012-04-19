package sorac.co.uk.calculator;

import com.google.common.base.Predicate;

public class NegativePredicate implements Predicate<Integer>{

    public boolean apply(Integer integer) {
        return integer < 0;
    }

}
