class StringCalculator {

  def add(String str) {
    sumar(numeros(str))
  }

  private def numeros(str) {
    def (tokens, operacion) = tokensYOperacion(str)
    def validos = []
    def negativos = []
    new Separador(tokens:tokens, operacion:operacion).separar validos, negativos
    if (negativos)
      throw new Exception("negatives not allowed: ${negativos}")

    return validos
  }

  private def separarNegativo(num, out) {
      if (num.toInteger() < 0)
        out << num
  }

  private def separarValido(num, out) {
      if (num.toInteger() < 1000)
        out << num.toInteger()
  }

  private def sumar(numeros) {
    def result = 0
    numeros.each {
      result += it.toInteger()
    }
    return result
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

class Separador {
  def tokens
  def operacion

  def separar(validos, negativos) {
    operacion.tokenize(",\n${tokens}").each { num ->
        separarNegativo num, negativos
        separarValido num, validos
    }
  }

  private def separarNegativo(num, out) {
      if (num.toInteger() < 0)
        out << num
  }

  private def separarValido(num, out) {
      if (num.toInteger() < 1000)
        out << num.toInteger()
  }


}

