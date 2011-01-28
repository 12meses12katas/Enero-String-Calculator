import static org.junit.Assert.*;
import static org.junit.runner.JUnitCore.runClasses;

import org.junit.Test;

public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    @Test
    public void returnsZeroWhenStringIsEmpty() {
        assertEquals(0, calculator.calculate(""));
        assertEquals(0, calculator.calculate(null));
    }

    @Test
    public void returnsTheSumOfTheNumbersIntoTheSequence() {
        assertEquals(3, calculator.calculate("1,2"));
    }

    @Test
    public void returnsTheSumOfNumbersSeparatedByNewLines() {
        assertEquals(3, calculator.calculate("1\n2"));
    }

    @Test
    public void supportsDifferentDelimiter() {
        assertEquals(3, calculator.calculate("//;\n1;2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWithNegativeNumbers() {
        calculator.calculate("1,-1");
    }

    @Test
    public void showsNegativeNumbersIntoTheMessage() {
        try {
            calculator.calculate("1,-2,-3");
            fail();
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();

            assertTrue(message.contains("-2"));
            assertTrue(message.contains("-3"));
        }
    }

    @Test
    public void doesNotSumNumbersBiggerThanThousand() {
        assertEquals(3, calculator.calculate("1,2,1001"));
    }

    @Test
    public void parsesSeparatorWithSquaredBraquets() {
        assertEquals(3, calculator.calculate("//[;]\n1;2"));
    }

    @Test
    public void supportsDelimitersWithMultipleCharacters() {
        assertEquals(3, calculator.calculate("//[%%%]\n1%%%2"));
    }

    @Test
    public void parsesMultipleSeparators() {
        assertEquals(6, calculator.calculate("//[;][%]\n1;2%3"));
    }

    public static void main(String... args) {
        System.out.println(runClasses(StringCalculatorTest.class).getFailures());
    }
}
