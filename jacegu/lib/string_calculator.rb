class String
  DELIMITER_PREFIX = '//'
  MULTIPLE_CHAR_DELIMITER_PREFIX = '//['
  MULTIPLE_CHAR_DELIMITER_SUFFIX = "]\n"
  DELIMITER_DELIMITER = ']['
  
  def defines_delimiter?
    start_with?(DELIMITER_PREFIX)
  end
 
  def defines_multiple_char_delimiter?
    start_with?(MULTIPLE_CHAR_DELIMITER_PREFIX) and
      include?(MULTIPLE_CHAR_DELIMITER_SUFFIX)
  end

  def delimiters
    return [delimiter_substring] unless include?(DELIMITER_DELIMITER)
    delimiter_substring.split(DELIMITER_DELIMITER) 
  end

  def delimiter_substring
    self[delimiter_substring_start..delimiter_substring_end] 
  end

  def delimiter_substring_start
    return single_char_delimiter_start unless defines_multiple_char_delimiter?
    MULTIPLE_CHAR_DELIMITER_PREFIX.length
  end

  def delimiter_substring_end
    return single_char_delimiter_end unless defines_multiple_char_delimiter?
    index(MULTIPLE_CHAR_DELIMITER_SUFFIX) - 1
  end

  def single_char_delimiter_position
    DELIMITER_PREFIX.length 
  end

  alias single_char_delimiter_start single_char_delimiter_position
  alias single_char_delimiter_end single_char_delimiter_position
end

module StringCalculator
  class Calculator
    def add(string)
      delimiters = Delimiters.new(string)
      Numbers.new(string, delimiters).add
    end
  end
  
  class Delimiters < Array
    DEFAULT_DELIMITERS = [',', "\n"]

    def initialize(string)
      include_delimiters_from(DEFAULT_DELIMITERS)
      include_delimiters_from(string.delimiters) if string.defines_delimiter?
    end

    def include_delimiters_from(delimiters)
      delimiters.each{ |delimiter| self << delimiter }
    end
  end

  class Numbers
    def initialize(string, delimiters)
      numbers_as_strings = string.split(Regexp.union(delimiters))
      @numbers = numbers_as_strings.map{ |string| string.to_i }
      raise "negatives not allowed: #{negatives.join(', ')}" if negatives.any?
    end

    def negatives
      @numbers.select{ |number| number < 0 }
    end
    
    def below_1001
      @numbers.select{ |number| number < 1001 }
    end

    def add
      below_1001.inject(0){ |sum, numbers| sum += numbers }
    end
  end
end
