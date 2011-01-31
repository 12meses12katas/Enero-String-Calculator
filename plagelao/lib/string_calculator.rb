class StringCalculator
  def add(string)
    raise_error_if_negative_numbers_in(string)
    split_numbers_from(string).inject(0){ |sum, numeral| sum + number_from(numeral)}
  end
  def raise_error_if_negative_numbers_in(string)
    negatives = split_numbers_from(string).select{|numeral| number_from(numeral) < 0}
    raise "negatives not allowed: #{negatives.join(', ')}" unless negatives.empty?
  end
  def split_numbers_from(string, separator = Separator.new)
    separator.split(string)
  end
  def number_from(string)
    Summand.new(string).number
  end
end
class Summand
  def initialize(string)
    @string = string
  end
  def number
    return @string.to_i if @string.to_i <= 1000
    0
  end
end
class Separator
  def split (string)
    string_without_separator_definition = string
    if defined_separators?(string)
      string_without_separator_definition = string.slice(string.index("\n")+1..-1)
    end
    strings = [string_without_separator_definition]
    separators(string).each do |separator|
      strings = internal_split(strings, separator)
    end
    strings
  end
  private
  def separators(string)
    separators = [',', "\n"]
    separators << defined_separators(string) if defined_separators?(string)
    separators.flatten
  end
  def internal_split(strings, separator)
    strings_without_separator = []
    strings.each do |string|
      if string.include?(separator)
        strings_without_separator << internal_split([string.slice(0...string.index(separator)), string.slice(string.index(separator)+separator.length..-1)], separator)
      else
        strings_without_separator << string
      end
    end
    strings_without_separator.flatten
  end
  def defined_separators?(string)
    string[0,2] == '//'
  end
  def defined_separators(string)
    defined_separators = string.scan(/\/\/\[(.*)\]\n.*/)
    return string[2,1] if defined_separators.empty?
    defined_separators[0].to_s.slice(2..-3).split('][')
  end
end

