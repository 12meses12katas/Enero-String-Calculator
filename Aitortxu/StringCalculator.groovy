class StringCalculator extends GroovyTestCase {
	
	Integer RESULTADO_CERO = 0
	String DELIMITADOR_POR_DEFECTO = ",", CARACTER_INICIO_DELIMITADOR = "//", DELIMITADOR_SALTO_CARRO = "\n"
	Integer POSICION_INICIO_DELIMITADOR = 2, POSICION_FIN_DELIMITADOR = 3 
	Integer POSICION_INICIO_CADENA_CON_DELIMITADOR = 4
	
	int Add(String texto){
		if (stringVacio(texto)){
			RESULTADO_CERO
		}else{
		   sumar numerosEnTexto(texto)
		}	
	}
	boolean stringVacio(String numbers){
		"".equals(numbers.trim())
	}
	String numerosEnTexto(String numbers){
		return reemplazarDelimitador(numbers, extaerDelimitador(numbers))
	}
	String extaerDelimitador(String numbers){
		if (numbers =~ /\/\//) return ((numbers =~ /\/\/.\n/).getAt(0).charAt(2)) 
		else return DELIMITADOR_POR_DEFECTO
		
	}
	String eliminarDelimitador(String numbers){
		return (numbers =~ /\/\/.\n/).replaceAll("")
	}
	String reemplazarDelimitador(String numbers, String delimitador){
		numbers = eliminarDelimitador(numbers)
		numbers= numbers.replace(delimitador, DELIMITADOR_POR_DEFECTO).replace(DELIMITADOR_SALTO_CARRO, DELIMITADOR_POR_DEFECTO)
	}
	Integer sumar(String numbers){
		numbers.split(DELIMITADOR_POR_DEFECTO) .inject(0){numero, resultado -> numero.toInteger() + resultado.toInteger() }
	}
	void testStringVacioDevuelveCero(){
		assertEquals 0, Add("")
		assertEquals 0, Add(" ")
	}
	void testStringConUnoDevulveUno(){
		assertEquals 1, Add("1")		
	}
	void testStringConDosDevulveDos(){
		assertEquals 2, Add("2")
	}
	void testStringConDosNumerosDevulveSuSuma(){
		assertEquals 3, Add("1,2")
		assertEquals 7, Add("2,5")
	}
	void testStringConTresNumerosDevuelveSuSuma(){
		assertEquals 5, Add("1,2,2")
		assertEquals 7, Add("2,4,1")
	}
	void testConRetornoDeCarro(){
		assertEquals 3, Add("1\n2")
	}
	void testConMarcaDeDelimitador(){
		assertEquals 3, Add("//;\n1;2")
	}

	
}