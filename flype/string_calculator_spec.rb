require 'rspec'
require 'string_calculator'

RSpec::Matchers.define :add do |expected|
  match do |actual|
    StringCalculator.add(actual) == expected
  end
end

describe StringCalculator do
  it "should sum 0 for ''" do
    ''.should add(0)
  end
    
  it "should sum 40 for '40'" do
    '40'.should add(40)
  end
  
  it "should sum 160 for '40,40,40,40'" do
    '40,40,40,40'.should add(160)
  end
  
  it "should sum 6 for '1\n2,3'" do
    '1\n2,3'.should add(6)
  end
  
  it "should sum 6 for '1\n,'" do
    '1\n,'.should add(1)
  end

  it "should sum 8 for '//ab\n2ab2ab4'", :fail => true do
    '//ab\n2ab2ab4'.should add(8)
  end
  
  it "should sum raise an exception for '-1,-3,1'" do
    lambda { StringCalculator.add('-1,-3,1') }.should raise_error(OnlyPositiveNumberAllowedError, '-1 and -3 are negative numbers')
  end
  
  it "should sum raise an exception for '//ab\n-2ab-2'" do
    lambda { StringCalculator.add('//ab\n-2ab-2') }.should raise_error(OnlyPositiveNumberAllowedError, '-2 and -2 are negative numbers')
  end

  it "should sum raise an exception for '2, 1001'" do
    '2,1001'.should add(2)
  end

  it "should sum raise an exception for '//[***]\n1***2***3'" do
    '//[***]\n1***2***3'.should add(6)
  end

  it "should sum raise an exception for '//[*][%]\n1*2%3'" do
    '//[*][%]\n1*2%3'.should add(6)
  end
end