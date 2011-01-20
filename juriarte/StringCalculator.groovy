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
    numeros.inject(0) { total, num -> total + num }
  }

}

class Separador {
  def tokens
  def operacion

  def Separador(texto) {
    (tokens, operacion) = tokensYOperacion(texto)
  }

  def separarNumerosValidosYNegativos(validos, negativos) {
    normalizar(operacion).tokenize(",\n").each { num ->
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
      tokens = str[2..str.indexOf('\n')-1]
      str = str[str.indexOf('\n')+1..-1]
    }
    return [tokens, str]
  }

  private def normalizar(operacion) {
    def regexp = ( tokens =~ /\[([\w]+)\]+/ ).collect {it[1]}.join('|')
    if (regexp)
      return operacion.replaceAll(regexp?:tokens, ",")
    else if (tokens)
      return operacion.replace(tokens, ",")
    else
      return operacion
  }

}

