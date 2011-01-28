require 'string_calculator'

describe StringCalculator do
  it "returns 0 when the string is empty" do
    subject.calculate('').should == 0
    subject.calculate(nil).should == 0
  end

  it "returns the sum of the numbers separated by comma" do
    subject.calculate('1,2').should == 3
  end

  it "also handles new lines instead of commas as separator" do
    subject.calculate("1\n2,3").should == 6
  end

  it "can use the delimiter present in the bigining of the string" do
    subject.calculate("//;\n1;2;3").should == 6
  end

  it "raises an error if negative values are passed" do
    lambda { subject.calculate('-1,2') }.should raise_error
  end

  it "includes all negative numbers into the error message" do
    begin
      subject.calculate('-1,-2')
    rescue
      $!.message.should include('-1')
      $!.message.should include('-2')
    end
  end

  it "ignores numbers bigger than 1000" do
    subject.calculate('1,2,1001').should == 3
  end

  it 'uses delimiters with the format “//[delimiter]\\n”' do
    subject.calculate("//[***]\n1***2***3").should == 6
  end

  it 'uses multiple delimiters with the format “//[delim1][delim2]\n”' do
    subject.calculate("//[*][%]\n1*2%3").should == 6
  end
end
