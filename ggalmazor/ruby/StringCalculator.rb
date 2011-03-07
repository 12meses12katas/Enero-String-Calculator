class StringCalculator
  SEPARATOR_COMMA = ","
  SEPARATOR_NEW_LINE = "\n"
  NUMBER_FORBIDDEN = 0
  NUMBER_INVALID = 1000
  CUSTOM_SEPARATOR_START = "//"
  CUSTOM_SEPARATOR_END = "\n"
  COMPLEX_SEPARATOR_START = "["
  COMPLEX_SEPARATOR_END = "]"
  def add(operands)
    return 0 if operands.empty?
    parseOperands(operands).reduce(:+)
  end
  def parseOperands(operands)
    operands = parseSeparators(operands)
    numbers = extractNumbers(operands)
    checkForbiddenNumbers(numbers.reject{ |number| !isForbiddenNumber?(number) })
    return numbers.reject{ |number| !isValidNumber?(number) }
  end
  def parseSeparators(operands) 
    if hasCustomSeparators?(operands)
      separators = extractSeparators(operands)
      operands = replaceSeparators(separators, operands)
    end
    return operands
  end
  def hasCustomSeparators?(operands) 
    operands[0..1] == CUSTOM_SEPARATOR_START
  end
  def extractSeparators(operands)
    if definesComplexSeparators?(operands)
      separators = operands.split(/\[(.+?)\]/).reject{ |separator| separator.empty? }
      separators = separators[1..separators.size()-2]
    else 
      separators = [operands[2..2]]
    end
    return separators
  end
  def definesComplexSeparators?(operands)
    operands[2..2] == COMPLEX_SEPARATOR_START
  end
  def replaceSeparators(separators, operands)
    operands = operands[operands.index(CUSTOM_SEPARATOR_END)+CUSTOM_SEPARATOR_END.size..-1]
    separators.each{ |separator| operands = operands.gsub(separator, SEPARATOR_COMMA) }
    return operands
  end
  def extractNumbers(operands)
    operands.split(/[#{SEPARATOR_COMMA},#{SEPARATOR_NEW_LINE}]/).map(&:to_i)
  end
  def checkForbiddenNumbers(numbers) 
    if numbers.size() > 0
      raise "Found some negative numbers: "+numbers.join(", ")
    end
  end
  def isForbiddenNumber?(number)
    number < NUMBER_FORBIDDEN
  end
  def isValidNumber?(number)
    number < NUMBER_INVALID
  end 
end
