class StringCalculator
  def add(string)
    string_calculator(string).add(string)
  end
  def string_calculator(string)
    calculator = DefaultStringCalculator.new
    defined_separators(string).each do |defined_separator|
      calculator = DefinedSeparatorStringCalculator.new(defined_separator, calculator)
    end
    calculator
  end
  def defined_separators(string)
    return [] unless string.start_with?('//')
    return string.scan(/\/\/\[(.*)\]\\n/)[0][0].split('][') unless string.scan(/\/\/\[(.*)\]\\n/).empty?
    [string[2]]
  end
end
module Splitter
  def numbers(string, separator)
    string.split(separator)
  end
end
module Additioner
  def sum(numbers, sum = 0, negatives = [])
    check_for_negatives_in(numbers)
    numbers.each do |summand|
      sum += yield(summand) unless summand.to_i > 1000
    end
    sum
  end
  def check_for_negatives_in(numbers, negatives = [])
    numbers.each do |summand|
      negatives << summand.to_i if summand.to_i < 0
    end
    raise "negative numbers not allowed: #{negatives.join(',')}" unless negatives.empty?
  end
end
class DefinedSeparatorStringCalculator
  include Additioner
  include Splitter
  def initialize(separator, next_calculator)
    @separator, @next_calculator = separator, next_calculator
  end
  def add(string)
    sum(numbers(string, @separator)) {|summand| @next_calculator.add(summand) }
  end
end
class DefaultStringCalculator
  include Additioner
  include Splitter
  def add(string)
    sum(numbers(string, /,|\\n/)) {|summand| summand.to_i }
  end
end
