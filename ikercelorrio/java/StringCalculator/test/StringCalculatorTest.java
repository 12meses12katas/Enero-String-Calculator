/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iker
 */
public class StringCalculatorTest {
    
    public StringCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    StringCalculator calc;

    @Before
    public void setUp() {
        calc = new StringCalculator();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Add method, of class StringCalculator.
     */
    @Test
    public void CadenaVaciaDevuelveCero() {
        assertEquals(0, calc.Add(""));
    }
    
    @Test
    public void NumeroSumaNumero()
    {
        assertEquals(1, calc.Add("1"));
        assertEquals(2, calc.Add("2"));
    }
    
    @Test
    public void NumerosSerparadosPorComas()
    {
        assertEquals(3, calc.Add("1,2"));
    }
}
