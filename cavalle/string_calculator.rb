class StringCalculator
  def add(string)
    delimiters = extract_delimiters(string)
    sum string.split(/#{delimiters.join('|')}/)
  end

  def sum(numbers)
    numbers.map!(&:to_i)
    numbers.reject!{|n| n > 1000}
    check_negatives numbers
    numbers.inject(0){|sum, n| sum + n}
  end

  def check_negatives(numbers)
    negatives = numbers.select{|n| n < 0}
    raise "negatives not allowed (#{negatives.join(', ')})" if negatives.any?
  end

  def extract_delimiters(string)
    head = string.slice!(%r{//.+\n}).to_s
    delimiters = head.scan(%r{\[?([^\]+])\]?}).flatten
    delimiters.map{|d| Regexp.quote(d)} + [",|\n"] 
  end
end

describe StringCalculator do
  it "returns 0 given an empty string" do
    subject.add("").should == 0
  end

  it "returns a number if a number is given" do
    subject.add("33").should == 33
  end

  it "returns the sum of two comma-separated numbers" do
    subject.add("1,2").should == 3
  end

  it "returns the sum of any number of numbers" do
    subject.add("1,2,3,4").should == 10
  end

  it "allows new line as delimiter" do
    subject.add("1\n2,3").should == 6
  end

  it "allows changing the delimiter" do
    subject.add("//;\n1;2;3").should == 6
  end

  it "doesn't allow negative numbers" do
    expect { subject.add("1,-2,-3,4") }.to raise_error(Exception, "negatives not allowed (-2, -3)")
  end

  it "ignores numbers over 1000" do
    subject.add("1000,1,1001").should == 1001
  end

  it "supports delimiters of any length" do
    subject.add("//[***]\n1***2***3").should == 6
  end

  it "supports multiple delimiters" do
    subject.add("//[%%][&&]\n1%%2&&3").should == 6
  end
end
