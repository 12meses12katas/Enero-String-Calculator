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
    public void UnNumero()
    {
        assertEquals(1, calc.Add("1"));
        assertEquals(2, calc.Add("2"));
    }
    
    @Test
    public void DosNumerosSerparadosPorComas()
    {
        assertEquals(3, calc.Add("1,2"));
        assertEquals(37, calc.Add("13,24"));
    }
    
    @Test
    public void VariosNumerosSerparadosPorComas()
    {
        assertEquals(10, calc.Add("1,3,2,4"));
        assertEquals(110, calc.Add("11,33,22,44"));
    }   
    
    @Test
    public void VariosNumerosSeparadosPorCommaOLF()
    {
        assertEquals(6, calc.Add("1\n2,3"));    
    }
    
    @Test
    public void VariosNumerosConSeparadorPersonalizado()
    {
        assertEquals(3, calc.Add("//;\n1;2"));    
    }
    
}
