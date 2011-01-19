class StringCalculator {

  def add(String texto) {
    sumar numerosDel(texto)
  }

  /***  ***/

  private def numerosDel(texto) {
    def validos = []
    def negativos = []
    new Separador(texto).separarNumerosValidosYNegativos validos, negativos
    if (negativos)
      throw new Exception("negatives not allowed: ${negativos}")


    return validos
  }

  private def sumar(numeros) {
    def result = 0
    numeros.each {
      result += it.toInteger()
    }
    return result
  }

}

class Separador {
  def tokens
  def operacion

  def Separador(texto) {
    (tokens, operacion) = tokensYOperacion(texto)
  }

  def separarNumerosValidosYNegativos(validos, negativos) {
    operacion.tokenize(",\n${tokens}").each { num ->
        añadirNumeroSiNegativo num, negativos
        añadirNumeroSiValido num, validos
    }
  }

  /**** ****/

  private def añadirNumeroSiNegativo(num, out) {
      if (num.toInteger() < 0)
        out << num
  }

  private def añadirNumeroSiValido(num, out) {
      if (num.toInteger() < 1000)
        out << num.toInteger()
  }

  private def tokensYOperacion(str) {
    def tokens
    if (str && str[0]=='/') {
      tokens = str[2..str.indexOf('\n')]
      str = str[str.indexOf('\n')..-1]
    }
    return [tokens, str]
  }

}

