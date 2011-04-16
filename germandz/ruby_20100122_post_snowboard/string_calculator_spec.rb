require "./string_calculator.rb"

describe "string_calculator" do

  it "should respond zero to a empty string" do
    "".add.should be 0
  end

  it "should respond the number if only contains a number" do
    "4".add.should be 4
    "27".add.should be 27
  end

  it "should sum the numbers when are separated by comma" do
    "4,3".add.should be 7
    "27,2".add.should be 29
    "27,2,100".add.should be 129
  end

  it "should sum the numbers when are separated by new line" do
    "4\n2".add.should be 6
    "4\n2,3".add.should be 9
  end

  it "should accept a custom delimiter" do
    "//:\n7:8".add.should be 15
  end
  
  it "should reject negative numbers with InvalidArgumentException" do
    lambda { "4,-2".add }.should raise_error
  end
  
  it "should report the negative numbers" do
    begin
      "4,-2".add
    rescue => error
      error.message.should include "-2"
    end
    begin
      "4,-2,2,-7".add
    rescue => error
      error.message.should include "-2"
      error.message.should include "-7"
    end
  end
  
  it "should ignore numbers greater than 1000" do
    "1001,2".add.should be 2
    "1000,2".add.should be 1002
  end
  
  it "should accept delimiters with multiples characters" do
    "//[***]\n6***3".add.should be 9
  end
  
  it "should accept multiple delimiters" do
    "//[*][%]\n1*2%3".add.should be 6
  end
  
  it "should accept multiple delimiters with multiples characters" do
    "//[*][%][abc]\n1*2%3abc3".add.should be 9
    "//[*][%][abc]\n1*2%3\n3".add.should be 9
  end

end