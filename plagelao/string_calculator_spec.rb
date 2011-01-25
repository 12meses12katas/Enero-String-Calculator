require 'rspec'
class StringCalculator
  def add(string)
    raise_error_if_negative_numbers_in(string)
    split_numbers_from(string).inject(0){ |sum, numeral| sum + number_from(numeral)}
  end
  def raise_error_if_negative_numbers_in(string)
    negatives = split_numbers_from(string).select{|numeral| number_from(numeral) < 0}
    raise "negatives not allowed: #{negatives.join(', ')}" unless negatives.empty?
  end
  def split_numbers_from(string, separator = Separator.new(string))
    string.split(separator)
  end
  def number_from(string)
    Summand.new(string).number
  end
end
class Summand
  def initialize(string)
    @string = string
  end
  def number
    return @string.to_i if @string.to_i <= 1000
    0
  end
end
class Separator < Regexp
  def initialize(string)
    super(regexp(string))
  end
  private
  def regexp(string)
    separators = [',', "\n"]
    separators.push(defined_separators(string)) if defined_separators?(string)
    Regexp.new(separators.join('|'))
  end
  def defined_separators?(string)
    string[0,2] == '//'
  end
  def defined_separators(string)
    defined_separators = string.scan(/\/\/\[(.*)\]\n.*/)
    return string[2,1] if defined_separators.empty?
    defined_separators[0].to_s.split('][')
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
        calculator.add("//[*][+]\n1*2+6").should == 9
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

