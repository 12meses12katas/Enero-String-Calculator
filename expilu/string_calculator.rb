#SOLUTION
#-----------------------------------------
require 'rspec'

class StringCalculator
  def self.add(numbers = '')    
    delimiter = numbers.match /^\/\/[^\d]+/
    numbers = (numbers << 0).split /[,\n#{delimiter.to_s.gsub(/[\/\/]/,'')}]/
    negative_numbers = numbers.select{|x| x.to_i < 0}
    raise "Negative numbers not allowed: #{negative_numbers.join(',')}" if negative_numbers.any?
    numbers.map(&:to_i).inject :+
  end
end

#-----------------------------------------
#TESTS
describe "StringCalculator.add" do
  #1) The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example '' or '1' or '1,2'
  it "returns 0 if nothing is passed - e.g. 0 for nil" do
    StringCalculator.add.should == 0
  end
  it "returns 0 if an empty string is passed - e.g. 0 for ''" do
    StringCalculator.add('').should == 0
  end
  it "returns the same number if only one is passed - e.g. 1 for '1'" do
    StringCalculator.add('1').should == 1
  end
  it "returns the sum of the numbers if two are passed separated by commas - e.g. 3 for '1,2'" do
    StringCalculator.add('1,2').should == 3
  end
  
  # 2) Allow the Add method to handle an unknown amount of numbers
  it "returns the sum of three numbers - e.g. 6 for '1,2,3'" do
    StringCalculator.add('1,2,3').should == 6
  end
  it "returns the sum of ten numbers - e.g. 10 for '1,1,1,1,1,1,1,1,1,1'" do
    StringCalculator.add('1,1,1,1,1,1,1,1,1,1').should == 10
  end
  
  #3) Allow the Add method to handle new lines between numbers (instead of commas).
  #  1. the following input is ok:  “1\n2,3”  (will equal 6)
  #  2. the following input is NOT ok:  “1,\n” (not need to prove it - just clarifying)
  it "returns the sum of two numbers separated by \\n - e.g. 3 for \"1\\n2\"" do
    StringCalculator.add("1\n2").should == 3
  end
  it "returns the sum of three numbers separated by a mix of , and \\n - e.g. 6 for \"1\\n2,3\"" do
    StringCalculator.add("1\n2,3").should == 6
  end
  
  #4) Support different delimiters
  #  1. to change a delimiter, the beginning of the string will contain a separate line that looks like this:   “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
  #  2. the first line is optional. all existing scenarios should still be supported
  it "returns the sum of two numbers using a custom delimiter - e.g. 3 for \"////;\\n1;2\"" do
    StringCalculator.add("////;\n1;2").should == 3
  end
  
  it "returns the sum of all numbers using a mix of custom and standard delimiters - e.g. 15 for \"////;\\n1,2\\n3;4;5\"" do
    StringCalculator.add("////;\n1,2\n3;4;5").should == 15
  end
  
  it "returns exception if a negative number is passed" do
    expect{ StringCalculator.add("-1,2,-3") }.to raise_error(/Negative numbers not allowed: -1,-3/)
  end
end
