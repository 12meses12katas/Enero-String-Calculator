require 'string_calculator'

describe 'StringCalculator' do

  describe "add" do

    before(:each) do
      @calculator = StringCalculator.new
    end

    it "should return 0 with an empty string" do
      @calculator.add("").should == 0
    end
    
    it "should return the number with a number" do
      @calculator.add('3').should == 3
    end

    it "should return the sum with 2 or more numbers" do
      @calculator.add('3,2').should == 5
      @calculator.add('1,2,3,4,5').should == 15
    end
    
    it "should work with the '\\n' delimiter" do
      @calculator.add("3\n2").should == 5
      @calculator.add("1,2\n3").should == 6
    end
    
    it "should allow a different delimiter" do
      @calculator.add("//;\n3;2").should == 5
      @calculator.add("//;\n1,2;3").should == 6
    end
    
    it "should raise an exception if any operand is negative" do
      lambda{@calculator.add("-3")}.should raise_error 'Negatives are not allowed: -3'
      lambda{@calculator.add("-3, -5")}.should raise_error 'Negatives are not allowed: -3, -5'
      lambda{@calculator.add("//;\n-1,2;-3")}.should raise_error 'Negatives are not allowed: -1, -3'
    end
    
    it "should ignore numbers bigger than 1000" do
      @calculator.add("1,2,3,1001,2000").should == 6
    end
    
    it "should allow delimiters with any length" do
      @calculator.add("//[***]\n1***2***3").should == 6
    end
    
    it "should allow multiple custom delimiters" do
      @calculator.add("//[*][%]\n1*2%3").should == 6
    end

    it "should allow multiple custom delimiters with length longer than one char" do
      @calculator.add("//[***][%%%%%]\n1***2%%%%%3").should == 6
    end

  end
end