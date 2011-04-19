require "rspec"

class Calculator
  def add(args)
    strnumbers, delimiter = extract_strnumbers_and_delimiter args
    numbers = get_number_list strnumbers, delimiter
    get_only_suitable_numbers numbers
    check_negatives_numbers numbers
    numbers.inject 0, :+
  end

  private

  def get_number_list(numbers_str, delimiter)
    numbers_str.split(delimiter).collect { |num| num.to_i }
  end

  def get_only_suitable_numbers(numbers)
    numbers.select! &:suitable_for_string_calculator? 
  end

  def check_negatives_numbers(numbers)
    negatives = numbers.select &:negative? 
    raise "negatives not allowed (#{negatives.join(', ')})" if negatives.any?
  end

  def extract_strnumbers_and_delimiter(args)
      delimiters = /[\n,]/ 
      text = args.dup
      first_line = text.slice!(%r{^//(.+)\n})
      delimiters = first_line.scan(%r{\[([^\]]+)\]}).flatten << delimiters if first_line
      return text, Regexp.union(delimiters)
  end

end

class Integer
  def negative?
    self < 0
  end

  def suitable_for_string_calculator?
    self <= 1000
  end

end



describe "String calculator" do
  before do
    @calculator = Calculator.new
  end

  it "empty should be 0" do
    @calculator.add("").should == 0
  end

  { "" => 0, "1" => 1, "345" => 345, "1,1" => 2, "3,4" => 7, "1,1,1" => 3,
    "1,2,3" => 6, "5\n2\n3" => 10, "123,78" => 201}.each do | numbers, result |
    it "adding #{numbers} should be #{result}" do
      @calculator.add(numbers).should == result
    end
  end

  it "delimiter , and \n should work" do
    @calculator.add("1\n2,3").should == 6
  end

  it "different delimiters specified in first line should work" do
    @calculator.add("//[%]\n2%6").should == 8
    @calculator.add("//[;]\n1;2").should == 3
    @calculator.add("//[+]\n8+12,43").should == 63
  end

  it "doesn't allow negative numbers" do
     expect {  @calculator.add("1\n-2\n-3\n4") }.to raise_error(Exception, "negatives not allowed (-2, -3)")
   end

  it "big numbers should be ignored" do
    @calculator.add("2,1001").should == 2
    @calculator.add("2,1000").should == 1002
  end

  it "delimiter can be of any length" do
    @calculator.add("//[***]\n1***2***3").should == 6
    @calculator.add("//[+=]\n1+=2+=3").should == 6
    @calculator.add("//[-]\n1-2-3").should == 6
    @calculator.add("//[--]\n1--2--3").should == 6
    expect {  @calculator.add("//[--]\n1--2---3") }.to raise_error(Exception, "negatives not allowed (-3)")
  end

  it "multiple delimiters are allowed" do
    @calculator.add("//[*][%]\n1*2%3").should == 6
    @calculator.add("//[*#][%%%]\n1*#2%%%5").should == 8
    @calculator.add("//[*][=>]\n1=>2*3").should == 6
  end

end
