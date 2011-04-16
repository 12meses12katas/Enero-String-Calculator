package kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringCalculatorTest {

	private StringCalculator calc = new StringCalculator();

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

	@Test
	public void testManyElements() throws Exception {
		assertEquals("adding multiple elements", 6, calc.add("1,2,3"));
	}

	@Test
	public void testNewLineDelimitier() throws Exception {
		assertEquals("adding with different delimiter", 6, calc.add("1\n2,3"));
	}

	@Test
	public void testExtraDelimitier() throws Exception {
		assertEquals("adding extra delimiter", 3, calc.add("//[;]\n1;2"));
		assertEquals("adding extra delimiter", 63, calc.add("//[+]\n8+12,43"));
	}

	@Test
	public void testNegativesThrowsException() throws Exception {
		try {
			calc.add("6,-8,3,-52");
			fail("testing negative numbers, shouldn't be here");
		} catch (Exception e) {
			String msg = e.getMessage();
			assertTrue("contains negative sentence",
					msg.contains("no se permiten negativos"));
			assertTrue("contains negative number", msg.contains("-8"));
			assertTrue("contains negative number", msg.contains("-52"));
		}
	}

	@Test
	public void testIgnoreGreaterThan1000() throws Exception {
		assertEquals("ignoring big numbers", 3, calc.add("1,2,1001"));
		assertEquals("ignoring more big numbers", 1001, calc.add("1,1000"));
	}

	@Test
	public void testMulticharDelimiters() throws Exception {
		assertEquals("adding multichar delimiters", 6,
				calc.add("//[***]\n1***2***3"));
		assertEquals("adding multichar delimiters", 6,
				calc.add("//[+=]\n1+=2+=3"));
	}

	@Test
	public void testMulticharDelimitersWithSubstraction() throws Exception {
		assertEquals(
				"adding multichar delimiters, trying to confuse with substraction",
				6, calc.add("//[-]\n1-2-3"));
		assertEquals(
				"adding multichar delimiters, trying to confuse with substraction",
				6, calc.add("//[--]\n1--2--3"));
		try {
			calc.add("//[--]\n1--2---3");
			fail("testing multichar delimiters with substraction, shouldn't be here");
		} catch (Exception e) {
			String msg = e.getMessage();
			assertTrue("contains negative sentence",
					msg.contains("no se permiten negativos"));
			assertTrue("contains negative number", msg.contains("-3"));
		}
	}

	@Test
	public void testSeveralDelimiters() throws Exception {
		assertEquals("adding several delimiters", 6,
				calc.add("//[*][=>]\n1=>2*3"));
	}
}
