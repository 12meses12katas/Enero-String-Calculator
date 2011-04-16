import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest
{
	@Test
	public void canCalculate0Numbers() throws Exception
	{
		assertEquals(0, StringCalculator.calculate(""));
	}

	@Test
	public void canCalculate1Number() throws Exception
	{
		assertEquals(0, StringCalculator.calculate("0"));
		assertEquals(1, StringCalculator.calculate("1"));
	}

	@Test
	public void canCalculate2Numbers() throws Exception
	{
		assertEquals(1, StringCalculator.calculate("0,1"));
		assertEquals(3, StringCalculator.calculate("1,2"));
	}

	@Test
	public void canCalculateUnknownAmountOfNumbers() throws Exception
	{
		assertEquals(55, StringCalculator.calculate("0,1,2,3,4,5,6,7,8,9,10"));
	}

	@Test
	public void canUseNewlineAsSeparator() throws Exception
	{
		assertEquals(1, StringCalculator.calculate("0\n1"));
		assertEquals(3, StringCalculator.calculate("0\n1,2"));
	}

	@Test
	public void canSupportDefinableDelimiters() throws Exception
	{
		assertEquals(10, StringCalculator.calculate("//;\n1;2;4;3"));
		assertEquals(10, StringCalculator.calculate("//.\n1.2.4.3"));
		assertEquals(21, StringCalculator.calculate("//*\n5*6*10"));
	}

	@Test
	public void mustThrowExceptionOnNegatives() throws Exception
	{
		try
		{
			StringCalculator.calculate("0\n-1");
			fail("Exception expected but not thrown");
		}
		catch (Exception e)
		{
			assertEquals("Negatives not allowed -1", e.getMessage());
		}
	}

	@Test
	public void mustThrowExceptionOnNegativesWithPrefix() throws Exception
	{
		try
		{
			StringCalculator.calculate("//*\n5*-6*10");
			fail("Exception expected but not thrown");
		}
		catch (Exception e)
		{
			assertEquals("Negatives not allowed -6", e.getMessage());
		}
	}

	@Test
	public void mustThrowExceptionOnSeveralNegativesWithPrefix() throws Exception
	{
		try
		{
			StringCalculator.calculate("//*\n5*-6*-10*-2");
			fail("Exception expected but not thrown");
		}
		catch (Exception e)
		{
			assertEquals("Negatives not allowed -6 -10 -2", e.getMessage());
		}
	}

}
