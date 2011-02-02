class StringCalculator {

  def add(String texto) {
    sumar numerosDel(texto)
  }

  /***  ***/

  private def sumar(numeros) {
    numeros.inject(0) { total, num -> total + num }
  }

  private def numerosDel(texto) {
      def (negativos, validos) = [[], []]

      normalizar(texto).each { num ->
        añadir(num).a(negativos).siNegativo()
        añadir(num).a(validos).siValido()
      }

      if (negativos)
        throw new Exception("negatives not allowed: ${negativos}")

      validos
  }

  /**
    * Devuelve [tokens, operacion]
    */
  private def separarTokensYOperacion(str) {
    if (str && str[0]=='/')
      [ str[2..str.indexOf('\n')-1],
        str[str.indexOf('\n')+1..-1] ]
    else
      [ null, str]
  }

  private def normalizar(texto) {
    def (tokens, operacion) = separarTokensYOperacion(texto)

    def regexp = ( tokens =~ /\[([+*\w]+)\]+/ ).collect {it[1]}.join('|')
    ["\\*":"\\\\*",
     "\\+":"\\\\+"].each { k,v ->
         regexp = regexp.replaceAll(k, v)
     }

    if (regexp)
      operacion = operacion.replaceAll(regexp, ",")
    else if (tokens)
      operacion = operacion.replace(tokens, ",")
    else
      operacion = operacion

    operacion.tokenize(",\n")
  }

  /**
    * Mini 'dsl' (:P) para separar numeros negativos y válidos
    */
  def añadir(num) {
      def obj = new Expando()
      obj.num = num
      obj.a = { c ->
        delegate.collection = c
        delegate
      }
      obj.siNegativo = {
        if (delegate.num.toInteger() < 0)
          delegate.collection << delegate.num.toInteger()
      }
      obj.siValido = {
        if (delegate.num.toInteger() < 1000)
          delegate.collection << delegate.num.toInteger()
      }
      obj
  }


}

