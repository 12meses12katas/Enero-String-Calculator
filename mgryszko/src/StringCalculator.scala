import java.util.regex.Pattern.quote

class StringCalculator {
  private val defaultSeparators = """,|\n"""

  def sum(expression: String): Int = {
    val (separators, summands) = splitIntoSeparatorsAndSummands(expression)

    val numericSummands = (separators.r).split(summands).filter(_ != "").map(_.toInt)
    val negativeSummands = numericSummands.filter(_ < 0)
    if (negativeSummands.nonEmpty) throw new IllegalArgumentException(negativeSummands.mkString(","))
    numericSummands.filter(_ <= 1000) .foldLeft(0)((sum, summand) => {
      sum + summand
    })
  }

  private def splitIntoSeparatorsAndSummands(expression: String) = {
    val separatorsRegex = """//(.+)\n""".r
    (separatorsRegex findFirstMatchIn expression) match {
      case Some(separatorsMatch) => (convertFoundSeparatorsToRegex(separatorsMatch.group(1)), separatorsMatch.after.toString)
      case None => (defaultSeparators, expression)
    }
  }

  private def convertFoundSeparatorsToRegex(separators: String) = {
    val singleSeparatorRegex = """\[?.+?\]?""".r
    val quotedSeparators = for(sep <- singleSeparatorRegex findAllIn separators)
      yield quote(sep.replaceAll("""[\[\]]""", ""))

    quotedSeparators.mkString("|")
  }
}