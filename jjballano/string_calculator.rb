class StringCalculator

   def calculate (string)
      string.nil? || string.empty? ? 0 : calculate_sum_of(string)
   end

   def calculate_sum_of(string)
      numbers = split_string_in_numbers(string)
      sum = 0
      numbers.inject(0) { |number, sum| sum += number }
      return sum
   end

   def split_string_in_numbers(string)
      string.split(',').map{ |number| number.to_i}
   end
end
