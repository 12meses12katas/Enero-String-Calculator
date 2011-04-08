require 'stringCalculator'
require 'test/unit'

class TestStringCalculator < Test::Unit::TestCase
	def setup
		@calc = StringCalculator.new
	end

	def test_blankParam
		assert_equal 0, @calc.Add("")
	end

	def test_oneParam
		param = "4"
		assert_equal Integer(param), @calc.Add(param)
	end

	def test_twoParam
		param = "4,3"
		assert_equal 7, @calc.Add(param)
	end

	def test_nParam
		param1 = "3,4,5"
		param2 = "3,4,5,9"

		assert_equal 12, @calc.Add(param1)
		assert_equal 21, @calc.Add(param2)
	end 
	
end
