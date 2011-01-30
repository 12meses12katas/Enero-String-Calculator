package micubiculo

object StringCalculator {
  def add(input: String): Integer = {
    if (isEmpty(input)) return 0
    else return input.split(",").foldLeft(0) {(sum, current) => sum + current.toInt }
  }

  private def isEmpty(input: String): Boolean = input.size == 0
}