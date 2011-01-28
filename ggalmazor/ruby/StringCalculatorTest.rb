require 'StringCalculator'

describe StringCalculator, "add" do
  
  before(:each) do
    @sc = StringCalculator.new
  end

  sumTestCases = [
    ["returns zero for an empty string", "", 0],
    ["returns the number for a string with a number", "1", 1],
    ["returns the sum for a string with two numbers", "1,2", 3],
    ["returns the sum for a string with three numbers", "1,2,3", 6],
    ["accepts new line as separator", "1\n2,3", 6],
    ["accepts a custom one char separator", "//;\n1;2", 3],
    ["accepts a custom many char separator", "//[***]\n1***2***3", 6],
    ["ignores any number bigger than 1000", "1,1001", 1],
    ["accepts many custom separators", "//[*][%]\n1*2%3", 6]
  ]

  sumTestCases.each{ |testCase|
    it testCase[0] do
      @sc.add(testCase[1]).should == testCase[2]
    end
  }

  it "raises a detailed exception for a string with negative number(s)" do
    lambda{ @sc.add("1,-2") }.should raise_error() { |error|
      error.message.should include("-2")
    }
  end
end
