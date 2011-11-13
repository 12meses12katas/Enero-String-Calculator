package com.marimon.katas.enero;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class NegativesNotAllowedException extends RuntimeException {

    private static final long serialVersionUID = 5785951305984620233L;

    private final Set<String> _invalidValuesSet = new TreeSet<String>();

    public NegativesNotAllowedException() {
        super();
    }

    public void addInvalidValue(final String... invalidValues) {
        _invalidValuesSet.addAll(Arrays.asList(invalidValues));
    }

    public Set<String> getInvalidValuesSet() {
        return _invalidValuesSet;
    }

}
