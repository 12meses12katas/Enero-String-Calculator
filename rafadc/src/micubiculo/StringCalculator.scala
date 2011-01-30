package micubiculo

object StringCalculator {
  val MAX_DELIMITERS = 2
  val DEFAULT_DELIMITER = ','
  val DEFAULT_DELIMITER_POSITION = 0
  val CARRIAGE_RETURN = '\n'
  val CARRIAGE_RETURN_POSITION = 1
  val NEW_DELIMITER_POSITION = 2
  val START_OF_STRING_TO_COMPUTE = 4
	
  def add(input: String): Integer = {
	var stringToCompute = input
    val delimiter = new Array[Char](MAX_DELIMITERS)
    delimiter(DEFAULT_DELIMITER_POSITION) = DEFAULT_DELIMITER
    delimiter(CARRIAGE_RETURN_POSITION) = CARRIAGE_RETURN
    if (isEmpty(input)) {
      return 0
    }
    if (isDefaultDelimiterChanged(input)) {
      delimiter(DEFAULT_DELIMITER_POSITION) = input(NEW_DELIMITER_POSITION)
      stringToCompute = input.substring(START_OF_STRING_TO_COMPUTE,input.size)
    }
    return computeSum(delimiter,stringToCompute)
  }

  private def isEmpty(input: String): Boolean = input.size == 0
  
  private def isDefaultDelimiterChanged(input: String): Boolean = input.size>START_OF_STRING_TO_COMPUTE && input.substring(0,NEW_DELIMITER_POSITION)=="//"

  private def computeSum(delimiter : Array[Char], input: String): Integer = {
    input.split(delimiter).foldLeft(0) { (sum, current) => sum + current.toInt }
  }
}