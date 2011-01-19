class StringCalculator {
  def string
  def tokens

  def add(String str) {
    operadores(str)
    sumar(numeros())
  }

  /**
    * Inicializa y configura en función de la entrada
    */
  private def operadores(str) {
    if (str && str[0]=='/') {
      tokens = str[2..str.indexOf('\n')]
      string = str[str.indexOf('\n')..-1]
    } else {
      string = str
    }
  }

  private def numeros() {
    def numeros = []
    def negatives = []
    string.tokenize(",\n${tokens}").each {
      def num = it.toInteger()
      if (num < 0)
        negatives << num
      if (num < 1000)
        numeros << num
    }
    if (negatives)
      throw new Exception("negatives not allowed: ${negatives}")

    return numeros
  }
  private def sumar(numeros) {
    def result = 0
    numeros.each {
      result += it.toInteger()
    }
    return result
  }
}

