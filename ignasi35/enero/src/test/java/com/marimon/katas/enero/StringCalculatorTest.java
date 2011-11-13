package com.marimon.katas.enero;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class StringCalculatorTest {

    private StringCalculator _app;

    @Before
    public void before() {
        _app = new StringCalculator();

    }

    @Test
    public void testNoParams() {
        Assert.assertEquals(0, _app.add(""));
    }

    @Test
    public void testOneParams() {
        Assert.assertEquals(1, _app.add("1"));
    }

    @Test
    public void testTwoParams() {
        Assert.assertEquals(7, _app.add("3,4"));
    }

    @Test
    public void testArbitraryParams() {
        Assert.assertEquals(55, _app.add("1,2,3,4,5,6,7,8,9,10"));
    }

    @Test
    public void testCRSeparator() {
        Assert.assertEquals(6, _app.add("1\n2,3"));
    }

    @Test
    public void testConfigurableSeparator() {
        Assert.assertEquals(3, _app.add("//;\n1;2"));
    }

}
