class StringCalculator
  class << self
    def add( numbers )
      # Add |\\n to the regexp if single quote strings are allowed
      numbers.split(/\n|,/).map(&:to_i).inject(0) { |sum, n| sum + n }
    end
  end
end