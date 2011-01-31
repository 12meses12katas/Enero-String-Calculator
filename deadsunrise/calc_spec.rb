require 'calc'

describe 'calc' do

  describe 'add' do

    before(:each) do
      @calc = Calc.new
    end

    it "should return 0 with an empty arg" do
      @calc.add("").should == 0
    end

    it "should return 1 with 1" do
      @calc.add('1').should == 1
    end

    it "should return 3 with 1,2" do
      @calc.add("1,2").should == 3
    end

    it "should sum 2 or more numbers" do
      @calc.add("1,2,3").should == 6
    end

    it "should allow new lines between numbers" do
      @calc.add("1,2,3").should == 6
      @calc.add("1\n2,3").should == 6
    end

    it "should allow different delimiters" do
      @calc.add("//;\n1").should == 1 
      @calc.add("//:\n1:2").should == 3
      @calc.add("//;\n1;2;3").should == 6

    end

    it "should rise an exception if numbers are negative" do
      lambda {@calc.add("-2")}.should raise_error(RuntimeError, 'negatives not allowed -2')
      lambda {@calc.add("1,-2,3")}.should raise_error(RuntimeError, 'negatives not allowed -2')
      lambda {@calc.add("1,-2,-3")}.should raise_error(RuntimeError, 'negatives not allowed -2,-3')
    end


    it "should ignore numbers bigger than 1000" do
      @calc.add("1,2000,2").should == 3
    end

    it "should allow delimiters longer than 1 char" do
      @calc.add("//[***]\n1***2***3").should == 6
    end


  end

end
