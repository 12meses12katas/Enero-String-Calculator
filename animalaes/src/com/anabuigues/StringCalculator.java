package com.anabuigues;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Implementaci�n sencilla de la calculadora para la kata de enero.<br />
 * <br />
 * La implementaci�n est� realizada usando java 6 est�ndar apoy�ndose en la
 * clase Scanner que permite trocear al vuelo un imput a partir de un o n
 * delimitadores. Para ello extrae los delimitadores y construye una expresi�n
 * reguar v�lida para el scanner al que se le solicitan los diferentes enteros
 * para ir sum�ndolos.
 * 
 * 
 * @author A. Buigues (@animalaes), H. Rodes (@hector_rodes)
 * 
 */
public class StringCalculator {

	public int add(String numbers) throws Exception {
		int returnValue;

		if (numbers == null || numbers.isEmpty()) {
			returnValue = 0;
		} else {
			ByteArrayInputStream bais = new ByteArrayInputStream(
					numbers.getBytes());
			Scanner sc = new Scanner(bais);

			String delimiters = null;

			// Delimiters
			if (numbers.startsWith("//")) {
				sc.useDelimiter("//|\n");
				if (sc.hasNext()) {
					delimiters = sc.next();
				}
			}

			delimiters = getScannerFormattedDelimiters(delimiters);

			// Process number lines with numbers to be added
			sc.useDelimiter(delimiters);
			int sum = 0;
			int currentNumber;
			List<Integer> negativeValues = new ArrayList<Integer>();
			while (sc.hasNext()) {
				currentNumber = sc.nextInt();
				if (currentNumber < 0) {
					negativeValues.add(currentNumber);
				} else if (currentNumber <= 1000) {
					sum += currentNumber;
				}
			}
			checkNegativeValues(negativeValues);
			returnValue = sum;
		}

		return returnValue;
	}

	/**
	 * M�todo auxiliar que comprueba si hay n�meros negativos y forma el mensaje
	 * 
	 * @param negativeValues
	 *            Lista de n�meros negativos
	 * @throws Exception
	 *             Si la lista de n�meros negativos contiene alg�n n�mero
	 */
	private void checkNegativeValues(List<Integer> negativeValues)
			throws Exception {
		if (negativeValues != null && negativeValues.size() > 0) {
			StringBuilder sb = new StringBuilder("negatives not allowed");
			for (int negative : negativeValues) {
				sb.append(" ").append(negative);
			}

			throw new Exception(sb.toString());
		}
	}

	/**
	 * Genera la cadena de delimitadores necesaria para ser usada en el scanner
	 * y as� poder procesar los n�meros.<br />
	 * Siempre introduce el \n como un delimitador v�lido.<br />
	 * En caso de que los delimiters sean vac�os o nulos usa la , y el \n como
	 * delimitadores por defecto
	 * 
	 * @param delimiters
	 *            Los delimitadores introducidos
	 * @return Cadena con los delimitadores en el formato que espera el Scanner
	 */
	private String getScannerFormattedDelimiters(String delimiters) {
		String delimitersExpression;
		if (delimiters == null || delimiters.isEmpty()) {
			delimitersExpression = ",|\n";
		} else {
			String currentDelimiter;
			StringBuilder sb = new StringBuilder();
			Scanner scDelimiters = new Scanner(delimiters).useDelimiter(Pattern
					.quote("]") + "|" + Pattern.quote("["));
			while (scDelimiters.hasNext()) {
				currentDelimiter = scDelimiters.next();
				if (!currentDelimiter.isEmpty()) {
					sb.append(Pattern.quote(currentDelimiter)).append("|");
				}
			}
			sb.append("\n");
			delimitersExpression = sb.toString();
		}

		return delimitersExpression;
	}

}
