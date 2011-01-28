# StringCalculator in Ruby 1.8.x

class StringCalculator
	def initialize(numbers)
		@numbers=numbers
		@result=nil
		@errors=''
		@delimiterDetectorRegExp=/\,|\n/
		detect_and_consume_custom_delimiter_if_any
	end

	def total_sum
		calculate_sum unless @result
		raise "Negative numbers detected: #{@errors}" unless @errors.empty?
		@result
	end

	private
	
	def calculate_sum
		@result = 0
		@numbers.split(@delimiterDetectorRegExp).each do |currentNumber|
		        currentNumber = currentNumber.to_i	
			@errors << " #{currentNumber}" if currentNumber < 0
			@result += currentNumber if currentNumber <= 1000
		end
	end

	def detect_and_consume_custom_delimiter_if_any
		if customDelimiterMatch = /^\/\/([^\n]+)\n/.match(@numbers) 
			extractAllDelimiters(customDelimiterMatch[1])
			@numbers = @numbers[customDelimiterMatch.end(0)..-1]
		end
	end

	def extractAllDelimiters(delimiterExpression)
		return appendDelimiter(delimiterExpression) if delimiterExpression.length == 1
		delimiterExpression.scan(/\[([^\]]+)\]/) do |delimiter|
			appendDelimiter(delimiter[0])
		end
	end

	def appendDelimiter(delimiter)
		@delimiterDetectorRegExp = Regexp.union(@delimiterDetectorRegExp, Regexp.new(Regexp.escape(delimiter)))
	end
end
