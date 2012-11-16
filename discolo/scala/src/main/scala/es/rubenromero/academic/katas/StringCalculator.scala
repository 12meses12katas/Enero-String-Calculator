package es.rubenromero.academic.katas

object StringCalculator {

  val DelimPattern = "//(.*)\n(.*)".r
  val defaultDelimiters = Set(",","\n")
    
  def addWithDelim(delimiters: Set[String], numbers: String): Int =
    if (numbers.isEmpty) 0
    else {
      val nums = numbers.split(delimiters.mkString("|")).map(_.toInt)
      if (nums.exists(_ < 0))
        throw new Error("negatives are not allowed")
      nums.foldLeft(0)(_ + _)
    }
    
  def add(numbers: String): Int = 
    if (numbers.matches(DelimPattern.toString)) {
        val args = DelimPattern unapplySeq numbers
        addWithDelim(Set(args.get(0)), args.get(1))
    } else
      addWithDelim(defaultDelimiters, numbers)
  
    
}