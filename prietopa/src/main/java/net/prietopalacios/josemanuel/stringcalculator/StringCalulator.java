package net.prietopalacios.josemanuel.stringcalculator;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * StringCalulator, visit:
 * <p>https://github.com/12meses12katas/Enero-String-Calculator</p>
 *
 */
public class StringCalulator {

    /**
     * log
     */
    private static Logger log = Logger.getLogger(StringCalulator.class);
    
    /**
     * <p>Lista de operandos de la suma</p>
     */
    private List<Double> sumandos = new ArrayList<Double>();
    
    /**
     * delimitador de los operandos
     */
    private String delimitador;

    public StringCalulator() {
    }

    /**
     * <p>Establece un delimitador valido para separar operandos de la suma.</p>
     * 
     * @param delimitador 
     */
    public void setDelimitador(String delimitador) {
        this.delimitador = delimitador;
    }

    /**
     * <p>Devuelve el valor "real" del operando</p>
     * 
     * @param sumando
     * @return
     * @throws Exception 
     */
    private double getSumando(String sumando) throws Exception {
        if (sumando == null) {
            log.info("Valor (null). Se inserta valor por defecto (0).");
            return 0;
        }
        if (sumando != null && sumando.equals("")) {
            log.info("Valor (''). Se inserta valor por defecto (0).");
            return 0;
        }

        Double numero = null;
        try {
            numero = Double.parseDouble(sumando);
        } catch (NumberFormatException e) {
            log.info("Valor desconocido (" + sumando + "). Se inserta valor por defecto (0).");
            return 0;
        }

        if (numero != null && numero < 0) {
            throw new Exception("negatives not allowed - (" + numero + ")");
        }

        return numero;
    }

    // ambito publico para test
    /**
     * <p>Establece los operandos pasados por parametro</p>
     * 
     * @param s
     * @throws Exception 
     */
    public void aniadeSumandos(String s) throws Exception {
        if (s == null) {
            sumandos.add(new Double("0"));
        } else {
            String[] posiblesSumandos = separaSumandos(s);
            for (int i = 0; i < posiblesSumandos.length; i++) {
                String str = posiblesSumandos[i];
                sumandos.add(getSumando(str));
            }
        }
    }

    /**
     * <p>Extrae los operandos pasados por parametro</p>
     * 
     * @param s
     * @return 
     */
    private String[] separaSumandos(String s) {
        s = s.replace("\n", ",");

        if (delimitador != null && !delimitador.equals("")) {
            s = s.replace(delimitador, ",");
        }

        String[] sumandosSeparados = s.split(",");
        return sumandosSeparados;
    }

    // para test
    /**
     * <p>Limpia la lista de operandos</p>
     */
    public void eliminaSumandos() {
        sumandos.clear();
    }

    // para test
    /**
     * <p>Devuelve el tamaño de la lista de operandos</p>
     * @return 
     */
    public int numeroSumandos() {
        return sumandos.size();
    }

    /**
     * <p>Separa los sumandos y los suma. Recoge dos sumandos, de manera que se puedan
     * sumar dos numeros directamente.</p>
     * <p>En estos parametros se pueden pasar mas sumandos, segun lo establecido en las normas.</p>
     * 
     * @param s1
     * @param s2
     * @return
     * @throws Exception 
     */
    public String suma(String s1, String s2) throws Exception {

        aniadeSumandos(s1);
        aniadeSumandos(s2);

        return suma();
    }

    /**
     * <p>Suma los sumandos contenidos en la lista</p>
     * 
     * @return 
     */
    public String suma() {
        double suma = 0;
        for (Double sumando : sumandos) {
            suma += sumando;
        }

        log.info(this.toString() + suma);
        eliminaSumandos();
        return String.valueOf(suma);
    }

    /**
     * <p>Añade un sumando o todos los que me pasan por parametro</p>
     * 
     * @param sumando
     * @throws Exception 
     */
    public void add(String sumando) throws Exception {
        log.info("Aniadiendo sumando :" + sumando + " ...");
        aniadeSumandos(sumando);
    }

    @Override
    public String toString() {
        String str = "Suma -> ";
        for (Double sumando : sumandos) {
            str += String.valueOf(sumando) + " + ";
        }

        if (str.endsWith(" + ")) {
            str = str.substring(0, str.length() - 3);
        }

        str += " = ";

        return str;
    }
}
