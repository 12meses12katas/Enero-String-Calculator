package net.prietopalacios.josemanuel.stringcalculator;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class StringCalulatorTest {

    private static final String MENSAJE_ERROR = "ERROR, valor no esperado - ";
    private static final String CERO = "0.0";
    private static StringCalulator sc;

    @BeforeClass
    public static void setUpClass() throws Exception {
        sc = new StringCalulator();
    }

    /**
     * si un test falla, y no pasa por suma(), donde tengo puesto la eliminacion
     * de los comandos, estos comandos pasan al siguiente test y descuadran 
     * las sumas
     */
    @After
    public void tearDown() {
        sc.eliminaSumandos();
    }

    @Test
    public void testAniadeSumandosYSuma() {
        try {
            sc.add("1\n");
            sc.add("1\n2,3");
            sc.add(CERO);
            sc.add("");
            sc.add(null);
            sc.add("1");
            sc.add("1.2");
            sc.add("5");

            assertEquals("", 10, sc.numeroSumandos());
            assertEquals(MENSAJE_ERROR, "14.2", sc.suma());

        } catch (Exception ex) {
            Logger.getLogger(StringCalulatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSuma() {
        try {
            assertEquals(MENSAJE_ERROR + 1, CERO, sc.suma(null, null));
            assertEquals(MENSAJE_ERROR + 2, CERO, sc.suma("0", null));
            assertEquals(MENSAJE_ERROR + 3, CERO, sc.suma(null, "0"));
            assertEquals(MENSAJE_ERROR + 4, CERO, sc.suma("", ""));
            assertEquals(MENSAJE_ERROR + 5, CERO, sc.suma(null, ""));
            assertEquals(MENSAJE_ERROR + 6, CERO, sc.suma("", null));
            assertEquals(MENSAJE_ERROR + 7, CERO, sc.suma("", "0"));
            assertEquals(MENSAJE_ERROR + 8, CERO, sc.suma("0", ""));
            assertEquals(MENSAJE_ERROR + 9, CERO, sc.suma("0", "0"));
            assertEquals(MENSAJE_ERROR + 10, "3.0", sc.suma("1,2", "0"));
            assertEquals(MENSAJE_ERROR + 11, "1.2", sc.suma("1.2", "0"));
            assertEquals(MENSAJE_ERROR + 12, "6.2", sc.suma("1.2", "5"));
            assertEquals(MENSAJE_ERROR + 13, "8.0", sc.suma("1,2", "5"));
            assertEquals(MENSAJE_ERROR + 14, "2.0", sc.suma("1", "1"));
            assertEquals(MENSAJE_ERROR + 15, "10.0", sc.suma("5", "5"));
            assertEquals(MENSAJE_ERROR + 16, "100.0", sc.suma("50", "50"));

        } catch (Exception ex) {
            Logger.getLogger(StringCalulatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAniadeSumandosYSuma4() {
        sc.setDelimitador(";");

        try {
            sc.add(";1\n;1;");
            sc.add("1\n");
            sc.add("1\n2,3");
            sc.add(CERO);
            sc.add("");
            sc.add(null);
            sc.add("1");
            sc.add("1.2");
            sc.add("5");

            assertEquals("", 14, sc.numeroSumandos());
            assertEquals(MENSAJE_ERROR, "16.2", sc.suma());

        } catch (Exception ex) {
            Logger.getLogger(StringCalulatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = Exception.class)
    public void testAniadeSumandosYSumaNumerosNegativos() throws Exception {
        sc.setDelimitador(";");
        sc.add(";-1\n;-1;");
    }
}
