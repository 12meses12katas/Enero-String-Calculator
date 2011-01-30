class StringCalculator
  
   def initialize
     @separator = ","
   end

   def calculate(string)
      if (string.nil?)
         0
      else
         change_separator(string)
         calculate_sum_of_numbers_in(string_without_header(string)) 
      end
   end

   def change_separator(string)
      if (string_has_header?(string))
         @separator = get_separator(string)
      end
   end

   def string_has_header?(string)
      string.start_with?("//")
   end

   def get_separator(string)
      initial = string.index("//") + 2
      final = string.index("\\n") - 1
      string[initial..final]
   end

   def string_without_header(string)
      if (string_has_header?(string))
         initial = string.index("\\n") + 2
         string[initial..-1]
      else
         string
      end
   end

   def calculate_sum_of_numbers_in(string)
      numbers = string.split(number_separator_char).collect { |number| number.to_i}
      return numbers.inject(0) { |sum, number| sum + number }
   end
   
   def number_separator_char
      Regexp.new("#{@separator}|\\n")
   end
end
