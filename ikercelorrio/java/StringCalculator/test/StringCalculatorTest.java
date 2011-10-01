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
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Add method, of class StringCalculator.
     */
    @Test
    public void CadenaVaciaDevuelveCero() {
        StringCalculator calc = new StringCalculator();
        assertEquals(0, calc.Add(""));
    }
}
