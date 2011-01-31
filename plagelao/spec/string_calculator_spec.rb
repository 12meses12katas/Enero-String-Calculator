require 'rspec'
require 'string_calculator.rb'

describe Separator do
 it "uses custom separators" do
   Separator.new.split("//[[[]\n10[[20").should == ["10", "20"]
  end
end

describe StringCalculator do
  let(:calculator){StringCalculator.new}

  it "returns 0 with an empty string" do
    calculator.add("").should == 0
  end
  context "with only one number returns the number" do
    it "returns 1 when 1" do
      calculator.add("1").should == 1
    end
    it "returns 10 when 10" do
      calculator.add("10").should == 10
    end
  end
  context "with more than one number returns the sum of the numbers" do
    it "returns 3 when 1,2" do
      calculator.add("1,2").should == 3
    end
    it "returns 4 when 2,2" do
      calculator.add("2,2").should == 4
    end
    it "returns 6 when 1,2,3" do
      calculator.add("1,2,3").should == 6
    end
  end
  context "works with different separators" do
    it "returns 5 when 2\n3" do
      calculator.add("2\n3").should == 5
    end
    it "returns 7 when //;\n3;4" do
      calculator.add("//;\n3;4").should == 7
    end
    it "returns 8 when //[separator]\n3separator5" do
      calculator.add("//[separator]\n3separator5").should == 8
    end
    context "works with more than one defined separator" do
      it "returns 9 when //[*][+]\n1*2+6" do
        calculator.add("//[*][+]\n1*2+6").should == 9
      end
      it "returns 10 when //[**][+++]\n2**2+++6" do
        calculator.add("//[**][+++]\n2**2+++6").should == 10
      end
      it "returns 100 when //[**][+++]\n20**20+++60" do
        calculator.add("//[**][+++]\n20**20+++60").should == 100
      end
    end
  end
  context "greater numbers than 1000 are ignored" do
    it "returns 0 when 1001" do
      calculator.add("1001").should == 0
    end
  end
  context "negative numbers are not allowed" do
    it "raise an exception when -1" do
      lambda{calculator.add("-1")}.should raise_error
    end
    it "raise an exception with 'negatives not allowed: -1,-2' when -1,-2,3" do
      lambda{calculator.add("-1,-2,3")}.should raise_error('negatives not allowed: -1, -2')
    end
  end
end

