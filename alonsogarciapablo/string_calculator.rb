class StringCalculator
  
  CUSTOM_DELIMITER_REGEXP = Regexp.new("\/\/(\\D)\\n")
  CUSTOM_LONG_DELIMITER_REGEXP = Regexp.new("\/\/\\[(([^\\]|\\D])+)\\]\\n")
  MULTIPLE_DELIMITERS_REGEXP = Regexp.new("\/\/(\\[.*\\])\\n")
  
  def add(operation)
    if operation == ''
      return 0
    end
    @operation = operation
    @delimiters = [",", "\n"]
    parse_operation
    return operate
  end

  private

  def parse_operation
    parse_delimiters
    parse_operands  
  end

  def parse_delimiters
    if matches = (@operation.match(CUSTOM_DELIMITER_REGEXP) || @operation.match(CUSTOM_LONG_DELIMITER_REGEXP))
      @delimiters << matches[1]
    elsif matches = @operation.match(MULTIPLE_DELIMITERS_REGEXP)
      delimiters = matches[1]
      delimiters.split(']').each do |delimiter|
        @delimiters << delimiter.delete('[')
      end
    end
  end
  
  def parse_operands
    extract_operands
    check_that_operands_are_positive
    remove_operands_greater_than(1000)
  end
  
  def extract_operands
    @operands = @operation.split(regexp_for_delimiters).collect{|operand| operand.to_i}    
  end
  
  def remove_operands_greater_than(limit)
    @operands.delete_if{|operand| operand > limit}
  end

  def check_that_operands_are_positive
    negative_operands = @operands.select{|operand| operand < 0}
    if negative_operands.size > 0
      raise "Negatives are not allowed: #{negative_operands.join(', ')}" 
    end
  end
  
  def regexp_for_delimiters
    Regexp.new(@delimiters.collect{|delimiter| Regexp.escape(delimiter)}.join('|'))
  end
  
  def operate
    result = 0
    @operands.each do |operand|
      result += operand
    end
    result
  end
  
end