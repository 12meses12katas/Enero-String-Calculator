/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Rule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    @Rule
    public ExpectedException thrown= ExpectedException.none();      
    
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
        assertEquals(6, calc.Add("//\n\n1\n2\n3"));    
        assertEquals(10, calc.Add("//s\n1s2s7"));
        assertEquals(156, calc.Add("//*\n31*52*73"));
    }
    
    @Test
    public void PrimerNumeroNegativoDeNumerosSeparadoPorComasProvocaExcepcionConMensaje()
    {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("negatives not allowed : -1");
        calc.Add("-1,2");
    }
    
    @Test
    public void SegundoNumeroNegativoDeNumerosSeparadoPorComasProvocaExcepcionConMensaje()
    {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("negatives not allowed : -2");
        calc.Add("1,-2");                
    }
    
    @Test
    public void NumeroNegativoConSeparadorPersonalizadoProvocaExcepcionConMensaje()
    {               
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("negatives not allowed : -3");
        calc.Add("//s\n1s-3s7");
    }
    
    @Test
    public void NumeroNegativoConSeparadorPersonalizadoMetacaracterProvocaExcepcionConMensaje()
    {               
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("negatives not allowed : -4");
        calc.Add("//*\n31*-4*73");
    }       
}
