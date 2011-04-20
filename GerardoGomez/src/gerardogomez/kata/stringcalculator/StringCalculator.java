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
	
	private String delimiter = ",";

	/**
	 * @param string
	 * @return
	 */
	public int add(String string) {
		int result=0;
		
			String[] numbers = string.split(delimiter);
			int[] values = parseNumbers(numbers);
			result = add(values); 
		
		return result;
	}

	private int[] parseNumbers(String[] numbers){
		int[] values = new int[numbers.length];
		
		for(int i=0; i<numbers.length; i++){
			try{
				String number = numbers[i];
				if(!"".equals(number.trim())){
					values[i] = Integer.valueOf(number);
				}
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("NaN");
			}
		}
		
		return values;
	}
		
	private int add(int[] values){
		int result = 0;
		for(int value: values){
			result += value;
		}
		return result;
	}
		

}
