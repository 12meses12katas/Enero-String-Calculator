class Calculator

   def sum_of(string_number)
      sum_of_each_digit(string_number) 
   end

   def sum_of_each_digit(string_number)
      sum = 0
      string_number.each_char { |digit| sum += digit.to_i }
      return sum
   end
end
