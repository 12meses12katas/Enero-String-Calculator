import scala.Array.canBuildFrom

object StringCalculator {

  val SpecialChars = "\\*|\\||\\.|\\+"
  val DelimPattern = "//(.*)\n(.*)".r
  val defaultDelimiters = Set(",","\n")
  val MultiDelimPat = "\\[(.*)\\]".r
    
  def preventSpecialChars(input: String): String = input.replaceAll(SpecialChars, "_")
  
  def addWithDelim(delimiters: Set[String], numbers: String): Int =
    if (numbers.isEmpty) 0
    else {
      val nums = preventSpecialChars(numbers).split(delimiters.map(preventSpecialChars(_)).mkString("|")).map(_.toInt).filter(_ <= 1000)
      if (nums.exists(_ < 0))
        throw new Error("negatives are not allowed")
      nums.foldLeft(0)(_ + _)
    }
  

  def add(numbers: String): Int = 
    if (numbers.matches(DelimPattern.toString)) {
        val args = DelimPattern unapplySeq numbers
        addWithDelim(identifyDelimiters(args.get(0)), args.get(1))
    } else
      addWithDelim(defaultDelimiters, numbers)
  
  /**
   * Identifies the delimiters and the rest of the sequence
   */ 
  def identifyDelimiters(input: String): Set[String] = 
    if(input matches MultiDelimPat.toString) {
      val args = MultiDelimPat unapplySeq input
      args.get(0).split("\\]\\[").toSet
	} else Set(input)
	
}