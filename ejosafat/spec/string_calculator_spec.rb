require "spec_helper"

describe "add" do
  it "should return 0 when string is empty" do
    StringCalculator.add('').should == 0
  end
  it "should return the sum when given one number only" do
    StringCalculator.add('2').should == 2
  end
  it "should return the sum of two numbers" do
    StringCalculator.add('3,4').should == 7
  end
end