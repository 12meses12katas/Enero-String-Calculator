class StringCalculator
  attr_reader :numbers, :delimiters
  
  def initialize(string)
    @delimiters = extract_delimiters(string)
    @numbers    = extract_numbers(string)
  end
  
  def calculate
    check_negative_numbers
    reject_numbers_bigger_than_1000
    sum
  end
  
  def extract_delimiters(string)
    ((string.match(/\/\/\[(.+)\]\[(.+)\]\\n/) || 
      string.match(/\/\/\[(.+)\]\\n/) || 
      string.match(/\/\/(.+)\\n/)).to_a[1..3] || [',']) << '\n'
  end

  def extract_numbers(string)  
    delimiters.each do |del|
      string = string.map{|s| s.split(del) }.flatten
    end
    string.map(&:to_i)
  end

  def check_negative_numbers
    errors = numbers.select{|n| n < 0 }
    if errors.any?
      msg = errors.join(' and ') + ' are negative numbers'
      raise OnlyPositiveNumberAllowedError.new(msg)
    end
  end

  def reject_numbers_bigger_than_1000
    numbers.reject!{|n| n > 1000 }
  end
    
  def sum
    return 0 if numbers.empty?
    numbers.reduce(:+)
  end
  
  class << self
    def add(string)
      new(string).calculate
    end    
  end
end

class OnlyPositiveNumberAllowedError < StandardError; end