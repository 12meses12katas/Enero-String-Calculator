class StringCalculator

	def Add(param)
		#It's a blank line of parameters
		return 0 if param == "" 

		#Initilize separator to base case
		separator = ','

		#Searching for a separator field within param string
		sep_fetch = param.split("\n")
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
			begin
				res += Integer(p)
			rescue
			end
		end

		return res
	end
end

s = StringCalculator.new
