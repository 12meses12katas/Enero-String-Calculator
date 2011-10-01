package calculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private Calculator instance;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        this.instance = new Calculator();
    }

    @Test
    public void emptyInput() {
        assertEquals(0, instance.add(""));
    }

    @Test
    public void oneNumber() {
        assertEquals(3, instance.add("3"));
    }

    @Test
    public void commaSeparatedNumbers() {
        assertEquals(3, instance.add("1,2"));
        assertEquals(6, instance.add("1,2,3"));
    }

    @Test
    public void commaOrNewlineSeparators() {
        assertEquals(6, instance.add("1\n2,3"));
    }

    @Test
    public void customDelimiter() {
        assertEquals(3, instance.add("//;\n1;2"));
    }

    @Test
    public void exceptionOnNegative() {
        thrown.expect(Calculator.NegativeInput.class);
        thrown.expectMessage("Negative input: -1");
        instance.add("1,-1,2");
    }

    @Test
    public void negativeInputExceptionCollectAllNegatives() {
        thrown.expect(Calculator.NegativeInput.class);
        thrown.expectMessage("Negative input: -2 -2");
        instance.add("1,-2,-2");
    }

    @Test
    public void ignoresGreaterThan1000() {
        assertEquals(3, instance.add("3,1001"));
        assertEquals(1003, instance.add("//|\n2|1000|1"));
    }

    @Test
    public void longCustomDelimiters() {
        assertEquals(6, instance.add("//[***]\n1***2***3"));
    }
    @Test
    public void multipleCustomDelimiters() {
        assertEquals(6, instance.add("//[*][%]\n1*2%3"));
    }
}
