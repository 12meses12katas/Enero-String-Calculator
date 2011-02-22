class StringCalculator
DEFAULT_DELIMITER = [","]

 def add(text)

   return 0 if text.empty?

   @delimiter = []
   @delimiter = set_delimiter(text)
   addends = extract_addends(text)
   check_for_negatives(addends)
   make_sum(addends)
 end

 def set_delimiter(cad)
   return DEFAULT_DELIMITER if cad[0] != "\\"
   if cad.match(/\[.*\]/)
     delimiters_expresion = cad.match(/\[.*\]/)[0]
     if delimiters_expresion.count("[") > 1
       return delimiters_expresion.gsub!(/[\[,\]]/, " ").split(" ")
     else
       return delimiters_expresion[1..delimiters_expresion.index("]")-1].split
     end
   elsif cad.match(/\\.*\n/)[0]
       return cad[1..cad.index("\n")-1].split
   end
 end

 def extract_addends(cad)
   cad = cad[cad.index("\n")+1..cad.length]  if cad[0] == "\\"
   cad.gsub!("\n", ",")
   @delimiter.each { |d| cad.gsub!(d, ",")}
   cad.split(",").map{ |a| a.to_i }

 end

 def make_sum(numbers)
   sum = 0
   numbers.each do |number|
     sum += number.to_i if number.to_i + sum < 1000
   end
   sum
 end

 def check_for_negatives(addends_chk)
   negatives = addends_chk.reject{ |n| n >= 0 }
   raise "Negatives are not allowed: #{negatives.join(', ')}" if !negatives.empty?
 end

end # class