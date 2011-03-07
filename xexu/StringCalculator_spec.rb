# RSpec file for String Calculator kata
# Author Christian Córdoba Andrés

require 'StringCalculator'

describe "StringCalculator" do
	context "Empty string" do
		it "Returns 0 if the param string is empty" do
			sc = StringCalculator.new
			sc.Add("").should == 0
		end
	end
	context "One number" do
		it "Returns the number zero" do
			sc = StringCalculator.new
			sc.Add("0").should == 0
		end
		it "Returns the number one" do
			sc = StringCalculator.new
			sc.Add("1").should == 1
		end
		it "Returns the number 123456" do
			sc = StringCalculator.new
			sc.Add("123456").should == 123456
		end
	end

	context "Two or more numbers" do
		it "Returns 1+2=3" do
			sc = StringCalculator.new
			sc.Add("1,2").should == 3
		end
		it "Works with 10 numbers" do
			sc = StringCalculator.new
			sc.Add("1,2,3,4,5,6,7,8,9,10")
		end
	end
	context "newline instead of , to separate numbers" do
		it "Returns 20+20=40" do
			sc = StringCalculator.new
			sc.Add("20\n20").should == 40
		end
	end
	context "Any delimiter" do
		it "works with ; too and returns 40" do
			sc = StringCalculator.new
			sc.Add("//;\n20;20").should == 40
		end
		it "works also with anything like H character" do
			sc = StringCalculator.new
			sc.Add("//H\n20H20").should == 40
		end
	end
	context "Negative numbers not allowed" do
		it "throw exception with 1 negative" do
			sc = StringCalculator.new
			expect{ sc.Add("//;\n20;-20") }.to raise_error "negative numbers are not allowed -20"
		end
		it "throw exception with n negative numbers" do
			sc = StringCalculator.new
			expect{ sc.Add("//;\n1;-2;3;-4;5;-6;7;-8;9;-10") }.to raise_error "negative numbers are not allowed -2,-4,-6,-8,-10"
		end
	end
end
