package kata.stringcalculator

import java.util.regex.Pattern
class NegativesNotAllowed extends RuntimeException

object calculator {
  val custom_single_delim_regexp = """^//([^\n])\n(.*)$""".r
  val custom_multi_delim_regexp = """^//\[([^\n]+)\]\n(.*)$""".r

  def add(numbers: String): Int = {
    sum(splitNumbers(numbers))
  }

  def splitNumbers(numbers: String): Iterable[String] = {
    val (separator, only_numbers) = numbers match {
      case custom_single_delim_regexp(separator, only_numbers) =>
        (Pattern.quote(separator), only_numbers)
      case custom_multi_delim_regexp(separator, only_numbers) =>
        (separator.split("""\]\[""").map(Pattern.quote).mkString("|"), only_numbers)
      case _ => (",|\\n", numbers)
    }
    only_numbers.split(separator)
  }

  def sum(numbers: Iterable[String]): Int = numbers match {
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
