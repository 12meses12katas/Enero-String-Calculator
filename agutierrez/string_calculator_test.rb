require './string_calculator.rb'

describe "StringCalculator", "add" do

  before(:each) do
    @kata = StringCalculator.new
  end

  Test_Data = [
    ["empty string makes 0", "", 0],
    ["string with number makes number", "1", 1],
    ["makes the sum of two separated by a comma numbers", "1,6", 7],
    ["makes the sum of four separated by a comma numbers", "1,3,4,6", 14],
    ["accepts the \n separator", "1,2\n5,6", 14],
    ["accepts custom, one char, separator between \\ and \n", "\\*\n2*3*4", 9],
    ["accepts custom, many char, separator between \\ and \n", "\\**\n2**3**4", 9],
    ["sum can not be greater than 1000", "300,400,500", 700],
    ["delimiters can be any number of chars", "\\[***]\n1***4***3", 8],
    ["various delimiters more than one char", "\\[aa][*][--]\n1aa2*3--4", 10]
  ]

  Test_Data.each do |testItem|

    it testItem[0] do
      @kata.add(testItem[1]).should == testItem[2]
    end
  end

  it "if any negative should raise an error showing wrong numbers" do
    lambda{@kata.add("-1")}.should raise_error 'Negatives are not allowed: -1'
    lambda{@kata.add("-1,2,-5,7")}.should raise_error 'Negatives are not allowed: -1, -5'
  end
  
end 