class StringCalculator
	def Add(string)
		if (string == "")
			0
		else
			negative = []
			delim = "\n"
			sum = 0
			if string.start_with? "//"
				delim = string[2].chr
				string = string.split("\n")[1]
			elsif string.include? ","
				delim = ","
			end
			string.split(delim).each { |n|
				if n.to_i < 0
					negative << n
				end
				sum+=n.to_i 
			}
			if negative.empty?
				sum
			else
				raise "numero negativo no esta permitido #{negative.join(",")}"
			end
		end
	end
end