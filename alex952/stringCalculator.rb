class StringCalculator

	def Add(param)
		return 0 if param == ""

		params = param.split(',')
		res = 0

		params.each do |p|
			res += Integer(p)
		end

		return res
	end
end

s = StringCalculator.new
