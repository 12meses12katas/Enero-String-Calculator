class StringCalculator {
  static def add(String str) {
    def token = ""
    if (str && str[0]=='/') {
      token = str[2..str.indexOf('\n')]
      str = str[str.indexOf('\n')..-1]
    }
    def result = 0
    def negatives = []
    str.tokenize(",\n${token}").each {
      def num = it.toInteger()
      if (num < 0)
        negatives << num
      else
        result += (num > 1000)?0:num
    }
    if (negatives)
      throw new Exception("negatives not allowed: ${negatives}")

    return result
  }
}

