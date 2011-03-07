package com.anabuigues;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author A. Buigues (@animalaes), H. Rodes (@hector_rodes)
 */
public class CalculatorTest {

	private StringCalculator calculator = new StringCalculator();

	@Test
	public void basicCalculator() throws Exception {
		Assert.assertEquals(0, calculator.add(""));
		Assert.assertEquals(1, calculator.add("1"));
		Assert.assertEquals(10, calculator.add("7,3"));
		Assert.assertEquals(10, calculator.add("7\n2,1"));
		Assert.assertEquals(27, calculator.add("5\n5\n8,2,4\n2,1"));
	}

	@Test
	public void simpleDelimiterCalculator() throws Exception {
		Assert.assertEquals(16, calculator.add("//[*]\n5*5*6"));
		Assert.assertEquals(20, calculator.add("//[*]\n5*5*6\n4"));
		Assert.assertEquals(20, calculator.add("//[*][;][,]\n5,5;6\n4"));
		Assert.assertEquals(20,
				calculator.add("//[*][;][pollofrito]\n5pollofrito5;6\n4"));
	}

	@Test
	public void bigNumbersCalculator() throws Exception {
		Assert.assertEquals(1005, calculator.add("//[*]\n5*1000*1001"));
	}

	@Test
	public void negativeNumbersCalculator() throws Exception {
		try {
			Assert.assertEquals(1005, calculator.add("//[*]\n5*-23*45*-34"));
		} catch (Exception e) {
			Assert.assertTrue("-23 must be in error message ", e.getMessage()
					.indexOf("-23") != -1);
			Assert.assertTrue("-34 must be in error message ", e.getMessage()
					.indexOf("-34") != -1);
		}
	}

	@Test
	public void notDefinedDelimiterCalculator() throws Exception {
		try {
			Assert.assertEquals(8, calculator.add("//[*]\n5;3"));
			Assert.fail("Delimiter ; is not allowed but it has been used");
		} catch (Exception e) {
		}
	}
}
