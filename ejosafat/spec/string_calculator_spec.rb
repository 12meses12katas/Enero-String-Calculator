require "spec_helper"

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
    StringCalculator.add('3,10,25,18').should == 56
  end
  
  # Task 3
  it "should handle , and \n as delimiters" do
    StringCalculator.add("3,10\n25\n18").should == 56
  end
  
  # Task 4
  it "should handle any delimiter" do
    StringCalculator.add("//;\n3,10\n25;18").should == 56
  end
end