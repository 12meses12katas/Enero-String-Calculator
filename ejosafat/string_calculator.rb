# Assumption: numbers is a doble quoted string with no invalid data
class StringCalculator
  class << self
    def add( numbers )
      regexp = "\n|,"
      if numbers =~ /(\/\/(.)\n)/
        regexp << "|#{$2}"
        numbers.gsub! $1.to_s, ''
      end
      numbers.split(/#{regexp}/).map(&:to_i).inject(0) { |sum, n| sum + n }
    end
  end
end