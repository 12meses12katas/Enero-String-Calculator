# Assumptions: 
# * numbers is a doble quoted string with no invalid data
# * - (minus) sign is not a valid delimiter
class StringCalculator
  class << self
    def add( numbers )
      calc = StringCalculator.new(numbers)
      calc.add
    rescue Exception => e
      raise e
    end
  end
  
  def initialize(numbers)
    @numbers_string = numbers
    @regexp = "\n|,"
  end
  
  def add
    extract_delimiter!
    number_list = @numbers_string.split(/#{@regexp}/).map(&:to_i)
    negative_numbers = number_list.select { |n| n < 0 }
    raise "negatives not allowed: #{negative_numbers.join(', ')}" unless negative_numbers.empty?
    number_list.inject(0) { |sum, n| sum + n }
  end
  
  private
  
  def extract_delimiter!
    if @numbers_string =~ /(\/\/(.)\n)/
      @regexp << "|#{$2}"
      @numbers_string.gsub! $1.to_s, ''
    end
  end
end