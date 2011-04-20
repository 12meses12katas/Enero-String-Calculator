/**
 * Autor: GerardoGomez (Gerardo Gómez-Caminero Ortega) 
 * Archivo: StringCalculator.java
 * Creado: 20/04/2011
 * Version: 1.0
 */
package gerardogomez.kata.stringcalculator;

/**
 * @author GerardoGomez (Gerardo Gómez-Caminero Ortega)
 *
 */
public class StringCalculator {

	/**
	 * @param string
	 * @return
	 */
	public int add(String string) {
		int result=0;
		
		try {
			result = Integer.valueOf(string);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
