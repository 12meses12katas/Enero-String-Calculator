class StringCalculator

	def Add(param)
		#It's a blank line of parameters
		return 0 if param == "" 

		#Initilize separator to base case
		separator = ','

		#Searching for a separator field within param string
		sep_fetch = param.split('\n')
		puts "El array #{sep_fetch} tiene #{sep_fetch.length} longitud y #{sep_fetch[0].start_with?("//")}"
		if sep_fetch.length > 1 && sep_fetch[0].start_with?("//") then
			#Exists the separator within params string
			separator = sep_fetch[0][2]
		else 
			#Search for separator
			param.each_char do |c|
				begin
					Integer(c)
				rescue
					separator = c
					break
				end
			end
		end


		#Split the parameters from a separator
		params = param.split(separator)
		res = 0

		#Add the parts, the operands
		params.each do |p|
			res += Integer(p)
		end

		return res
	end
end

s = StringCalculator.new
