class String

  DEFAULT_DELIMITER = ','
  NEW_LINE = "\n"
  LIMIT = 1000
  DELIMITER_SEPARATOR = "]["
  BEGIN_CUSTOM_DELIMITERS = "//"
  END_CUSTOM_DELIMITERS = "\n"

  def add
    reject_negatives
    numbers_up_to_limit.inject(0) { |sum, number| sum += number.to_i }
  end
  
  def reject_negatives
    negatives = numbers.select { |number| number.to_i < 0  }
    error_msg = 'negatives not allowed: ' + negatives.to_s
    raise ArgumentError.new(error_msg) unless negatives.empty?
  end  

  def numbers_up_to_limit
    numbers.select { |number| number.to_i <= LIMIT }
  end

  def numbers
    normalize_delimiters.split(DEFAULT_DELIMITER)
  end
  
  def normalize_delimiters
    delimiters.inject(numbers_part) { | numbers, delimiter | normalize_delimiter numbers, delimiter }
  end

  def normalize_delimiter string, delimiter
    string.sub delimiter, DEFAULT_DELIMITER
  end
  
  def custom_delimiter?
    self.start_with? BEGIN_CUSTOM_DELIMITERS
  end
  
  def parse_delimiters string
    return [string] if string.length == 1
    string[1..string.length-2].split(DELIMITER_SEPARATOR)
  end
  
  def custom_delimiters
    return [] unless custom_delimiter?
    parse_delimiters delimiters_part
  end
  
  def delimiters_part
    self.partition(END_CUSTOM_DELIMITERS)[0][2..self.length]
  end
  
  def numbers_part
    return self unless custom_delimiter?
    self.partition(END_CUSTOM_DELIMITERS)[2]
  end
  
  def delimiters
    custom_delimiters << NEW_LINE
  end
end