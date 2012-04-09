class StringCalculator
	def Add(string)
		if (string == "")
			0
		else
			negative = []
			delim = "\n"
			sum = 0
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