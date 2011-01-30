package kata.stringcalculator

class NegativesNotAllowed extends RuntimeException

object calculator {
  val custom_single_delim_regexp = """^//([^\n])\n(.*)$""".r
  val custom_multi_delim_regexp = """^//\[([^\n]+)\]\n(.*)$""".r

  def add(numbers: String): Int = {
    sum(splitNumbers(numbers))
  }

  def splitNumbers(numbers: String): List[String] = {
    val dp = numbers match {
      case custom_single_delim_regexp(separator, only_numbers) =>
        ("\\Q" + separator + "\\E", only_numbers)
      case custom_multi_delim_regexp(separator, only_numbers) =>
        ("\\Q" + separator.split("""\]\[""").mkString("\\E|\\Q") + "\\E", only_numbers)
      case _ => (",|\\n", numbers)
    }
    val separator = dp._1
    val only_numbers = dp._2
    only_numbers.split(separator).toList
  }

  def sum(numbers: List[String]): Int = numbers match {
    case "" :: Nil => 0
    case n :: Nil => toInt(n)
    case a :: tail => toInt(a) + add(tail.mkString(","))
  }

  def toInt(s: String) = {
    val v = s.toInt
    if (v < 0) throw new NegativesNotAllowed()
    else if (v > 1000) 0
    else v
  }
}
