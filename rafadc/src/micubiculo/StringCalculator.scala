package micubiculo

object StringCalculator {
  def add(input: String): Integer = {
	val separators = new Array[Char](2)
	separators(0) = ','
	separators(1) = '\n'
    if (isEmpty(input)) return 0
    else return input.split(separators).foldLeft(0) {(sum, current) => sum + current.toInt }
  }

  private def isEmpty(input: String): Boolean = input.size == 0
}