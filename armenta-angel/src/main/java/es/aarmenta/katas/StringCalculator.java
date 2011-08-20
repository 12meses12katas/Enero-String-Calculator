/**
 * 
 */
package es.aarmenta.katas;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aarmenta
 * 
 */
public class StringCalculator {

	// ------------------------------------------------------------- Constantes

	private static final String DELIMITADORES_DEFECTO = ",\n";

	private static final String INICIO_SELECTOR_DELIMITADOR = "//";

	private static final String FIN_SELECTOR_DELIMITADOR = "\n";

	private static final String INICIO_FIN__DELIMITADOR = "[]";

	private static final int MAXIMO_SUMANDO = 1000;

	// -------------------------------------------------------------- Atributos

	private List<String> delimitadores;

	private List<Integer> sumandos;

	// ------------------------------------------------------- Metodos publicos

	public int add(String inputString) throws NegativesNotAllowedException {
		int resultado = 0;

		resetCalc();

		if (inputString.trim().length() == 0) {
			resultado = 0;
		} else {
			fijarDelimitadores(inputString);
			obtenerSumandos(inputString);
			validaNoNegativos();
			resultado = calcular();
		}

		return resultado;
	}

	// ------------------------------------------------------- Metodos privados

	private void resetCalc() {
		delimitadores = new ArrayList<String>();
		sumandos = new ArrayList<Integer>();
	}

	private void fijarDelimitadores(String inputString) {
		String strDelimitadores;
		StringTokenizer st;

		if (inputString.startsWith(INICIO_SELECTOR_DELIMITADOR)) {
			strDelimitadores = inputString.substring(
					INICIO_SELECTOR_DELIMITADOR.length(),
					inputString.indexOf(FIN_SELECTOR_DELIMITADOR));

			st = new StringTokenizer(strDelimitadores, INICIO_FIN__DELIMITADOR);
			if (st.countTokens() > 0) {
				/*
				 * Separadores de longitud mayor que uno o multiples
				 * delimitadores o ambos
				 */
				while (st.hasMoreTokens()) {
					delimitadores.add(st.nextToken());
				}
			} else {
				/*
				 * Se define explicitamente un unico delimitador de longitud uno
				 */
				delimitadores.add(strDelimitadores);
			}
		} else {
			// No se definen delimitadores especificos para los sumandos
			delimitadores.add(DELIMITADORES_DEFECTO);
		}
	}

	private void obtenerSumandos(String inputString) {
		StringTokenizer st;
		String strSumandos;
		int sumando;
		String token = DELIMITADORES_DEFECTO.substring(0, 1);
		

		// Me quedo con la parte de los sumandos de la cadena de entrada
		if (inputString.startsWith(INICIO_SELECTOR_DELIMITADOR)) {
			strSumandos = inputString.substring(inputString
					.indexOf(FIN_SELECTOR_DELIMITADOR) + 1);
		} else {
			strSumandos = inputString;
		}

		// Si hay mas de un tipo de delimitador, sustiuyo cada uno de ellos por
		// uno comun y asi poder simplificar la busqueda de sumandos
		if (delimitadores.size() > 1) {
			for (int i = 0; i < delimitadores.size(); i++) {
				String delim = delimitadores.get(i);
				int indexA = 0;
				int indexB = 0;
				do {
					indexA = strSumandos.indexOf(delim);
					if (indexA >= 0) {
						indexB = indexA + delim.length();
						strSumandos = strSumandos.substring(0, indexA) + token
								+ strSumandos.substring(indexB);
					}
				} while (indexA >= 0);

			}
		} else {
			token = delimitadores.get(0);
		}

		// Extraemos los sumandos
		st = new StringTokenizer(strSumandos, token);
		while (st.hasMoreTokens()) {
			sumando = Integer.valueOf(st.nextToken()).intValue();
			if (sumando < MAXIMO_SUMANDO) {
				sumandos.add(Integer.valueOf(sumando));
			}
		}
	}

	private void validaNoNegativos() throws NegativesNotAllowedException {
		String salida = "";

		for (int i = 0; i < sumandos.size(); i++) {
			Integer sumando = (Integer) sumandos.get(i);
			if (sumando.intValue() < 0) {
				if (salida.length() > 0) {
					salida += ",";
				}
				salida += sumando;
			}
		}

		if (salida.length() > 0) {
			throw new NegativesNotAllowedException("Negatives not allowed: "
					+ salida);
		}
	}

	private int calcular() {
		int resultado = 0;

		for (int i = 0; i < sumandos.size(); i++) {
			resultado += (Integer) sumandos.get(i);
		}

		return resultado;
	}
}
