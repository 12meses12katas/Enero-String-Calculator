require "spec_helper"
describe StringCalculator do
  let( :simple_numbers ) { '3,10,25,18' }
  let( :delimited_numbers ) { "//;\n3,10\n25;18" }
  describe "add" do
    # Task 1
    it "should return 0 when string is empty" do
      StringCalculator.add('').should == 0
    end
    it "should return the sum when given one number only" do
      StringCalculator.add('2').should == 2
    end
    it "should return the sum of two numbers" do
      StringCalculator.add('3,4').should == 7
    end
  
    # Task 2
    it "should handle any amount of numbers" do
      StringCalculator.add(simple_numbers).should == 56
    end
  
    # Task 3
    it "should handle , and \n as delimiters" do
      StringCalculator.add("3,10\n25\n18").should == 56
    end
  
    # Task 4
    it "should handle any delimiter" do
      StringCalculator.add(delimited_numbers).should == 56
    end
  
    # Task 5
    it "should raise an exception if there is a negative number including that number" do
      expect {
        StringCalculator.add('3,-10,25,18')
      }.to raise_error "negatives not allowed: -10"
    end
    it "should include in the exception message all the negative numbers" do
      expect {
        StringCalculator.add('3,-10,25,-18')
      }.to raise_error "negatives not allowed: -10, -18"
    end
  end

  describe "initialize" do
    it "should assign numbers_string instance variable" do
      calc = StringCalculator.new(simple_numbers)
      calc.instance_variable_get(:@numbers_string).should == simple_numbers
    end
    it "should initialize regexp expression" do
      calc = StringCalculator.new(simple_numbers)
      calc.instance_variable_get(:@regexp).should == "\n|,"
    end
  end
  
  describe "extract_delimiter!" do
    it "should delete delimiter definition if present" do
      calc = StringCalculator.new(delimited_numbers)
      calc.send(:extract_delimiter!)
      calc.instance_variable_get(:@numbers_string).should == "3,10\n25;18"
    end
    it "should lets string unmodified if there isn't a delimiter definition" do
      calc = StringCalculator.new(simple_numbers)
      calc.send(:extract_delimiter!)
      calc.instance_variable_get(:@numbers_string).should == simple_numbers
    end
    it "should modify regexp if delimiter is present" do
      calc = StringCalculator.new(delimited_numbers)
       calc.send(:extract_delimiter!)
      calc.instance_variable_get(:@regexp).should == "\n|,|;"
    end
    it "should leave regexp unmodified if there isn't a delimiter" do
      calc = StringCalculator.new(simple_numbers)
      calc.send(:extract_delimiter!)
      calc.instance_variable_get(:@regexp).should == "\n|,"
    end
  end
end