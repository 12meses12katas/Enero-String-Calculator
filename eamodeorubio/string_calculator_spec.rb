require './string_calculator.rb'

describe StringCalculator do
	context "Sums 0, 1 or 2 numbers" do
		it "returns 0 when passed an empty string" do
			StringCalculator.new('').total_sum.should == 0
		end
		it "returns the number when passed only one number" do
			StringCalculator.new('1').total_sum.should == 1
			StringCalculator.new('12').total_sum.should == 12
			StringCalculator.new('24').total_sum.should == 24
			StringCalculator.new('35').total_sum.should == 35
		end
		it "returns the sum of two numbers separated by commas" do
			StringCalculator.new('1,2').total_sum.should == 3
			StringCalculator.new('12,34').total_sum.should == 46
			StringCalculator.new('24,10').total_sum.should == 34
			StringCalculator.new('46,34').total_sum.should == 80
		end
	end

	context "Sums any number of integers" do
		it "returns 13 when passed '1,10,2'" do
			StringCalculator.new("1,10,2").total_sum.should == 13
		end
		it "returns 254 when passed '21,1,232'" do
			StringCalculator.new("21,1,232").total_sum.should == 254
		end
		it "returns 112 when passed '100,5,6,1'" do
			StringCalculator.new("100,5,6,1").total_sum.should == 112
		end
		it "returns 153 when passed '10,11,102,30'" do
			StringCalculator.new("10,11,102,30").total_sum.should == 153
		end
	end

	context "It deals with \\n as a delimiter" do
		it "returns 46 when passed '12\\n34'" do
			StringCalculator.new("12\n34").total_sum.should == 46
		end
		it "returns 13 when passed '1,10\\n2'" do
			StringCalculator.new("1,10\n2").total_sum.should == 13
		end
		it "returns 254 when passed '21\\n1,232'" do
			StringCalculator.new("21\n1,232").total_sum.should == 254
		end
		it "returns 112 when passed '100\\n5\\n6\\n1'" do
			StringCalculator.new("100\n5\n6\n1").total_sum.should == 112
		end
		it "returns 153 when passed '10,11\\n102\\n30'" do
			StringCalculator.new("10,11\n102\n30").total_sum.should == 153
		end
		it "returns 112 when passed '100\\n5\\n6,1'" do
			StringCalculator.new("100\n5\n6,1").total_sum.should == 112
		end
		it "returns 153 when passed '10\\n11,102\\n30'" do
			StringCalculator.new("10\n11,102\n30").total_sum.should == 153
		end
	end

	context "It deals with an arbitrary delimiter specified with '//delimiter\\n'" do
		it "returns 46 when passed '//;\\n12;34'" do
			StringCalculator.new("//;\n12;34").total_sum.should == 46
		end
		it "returns 13 when passed '//*\\n1*10\\n2'" do
			StringCalculator.new("//*\n1*10\n2").total_sum.should == 13
		end
		it "returns 13 when passed '//*\\n1\\n10*2'" do
			StringCalculator.new("//*\n1\n10*2").total_sum.should == 13
		end
		it "returns 153 when passed '//-\\n10-11\\n102-30'" do
			StringCalculator.new("//-\n10-11\n102-30").total_sum.should == 153
		end
	end

	context "It raises an error when passed negative numbers including the offending numbers" do
		it "raises an error containing -22 when passed '1,-22'" do
			lambda { StringCalculator.new("1,-22").total_sum }.should raise_error { |error| error.message.should include("-22") }
		end
		it "raises an error containing -22,-2 and -15  when passed '1,-22,33,-2,44,-15'" do
			lambda { StringCalculator.new("1,-22,33,-2,44,-15").total_sum }.should raise_error { |error| error.message.should include("-22","-2","-15") }
		end
	end

	context "Ignores numbers above 1000" do
		it "returns 35 when passed '1,1002,34'" do
			StringCalculator.new("1,1002,34").total_sum.should == 35
		end
		it "returns 135 when passed '1,1002,34,50,2353,50'" do
			StringCalculator.new("1,1002,34,50,2353,50").total_sum.should == 135
		end
	end

	context "It deals with a custom delimiter of variable length specified with '//[delimiter]\\n'" do
		it "returns 46 when passed '//[;**]\\n12;**34'" do
			StringCalculator.new("//[;**]\n12;**34").total_sum.should == 46
		end
		it "returns 13 when passed '//[*-*]\\n1*-*10\\n2'" do
			StringCalculator.new("//[*-*]\n1*-*10\n2").total_sum.should == 13
		end
		it "returns 13 when passed '//[iop]\\n1\\n10iop2'" do
			StringCalculator.new("//[iop]\n1\n10iop2").total_sum.should == 13
		end
		it "returns 153 when passed '//[-99-]\\n10-99-11\\n102-99-30'" do
			StringCalculator.new("//[-99-]\n10-99-11\n102-99-30").total_sum.should == 153
		end
	end

	context "It deals with a several custom delimiter '//[delimiter1][delimiter2]\\n'" do
		it "returns 76 when passed '//[;**][%]\\n12;**34%10\n20'" do
			StringCalculator.new("//[;**][%]\n12;**34%10\n20").total_sum.should == 76
		end
		it "returns 53 when passed '//[*-][$$][&]\\n1*-*10\\n2$$15&25'" do
			StringCalculator.new("//[*-*][$$][&]\n1*-*10\n2$$15&25").total_sum.should == 53
		end
	end
end
