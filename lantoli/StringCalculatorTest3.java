package kata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculatorTest3 {

	private StringCalculator3 calc = new StringCalculator3();

	@Test
	public void testAddEmpty() throws Exception {
		assertEquals("adding empty string", 0, calc.add(""));
	}

	@Test
	public void testSingleElement() throws Exception {
		assertEquals("adding simple element", 1, calc.add("1"));
		assertEquals("adding more simple elements", 345, calc.add("345"));
	}

	@Test
	public void testTwoElementsSum() throws Exception {
		assertEquals("adding two elements", 3, calc.add("1,2"));
		assertEquals("adding two more elements", 201, calc.add("123,78"));
	}
}
