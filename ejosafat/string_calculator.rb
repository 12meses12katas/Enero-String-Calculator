class StringCalculator
  class << self
    def add( numbers )
      numbers.split(',').map(&:to_i).inject(0) { |sum, n| sum + n }
    end
  end
end