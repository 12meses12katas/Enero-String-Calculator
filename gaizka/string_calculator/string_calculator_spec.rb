$: << File.dirname(__FILE__)

require 'string_calculator'

describe StringCalculator do
  it "(for an empty string it will return 0)" do
    subject.add("").should == 0
    subject.add("0").should == 0
  end

  it "simple sums" do
    subject.add("1").should == 1
    subject.add("1,2").should == 3
  end

  it "any number of ints" do
    subject.add( ([2] * 10).join(",")).should == 20
  end

  ["1,2,3", "1\n2\n3", "1,2\n3", "1\n2,3"].each do |valid|
    it "should work using different delimiters (#{valid.inspect})" do
      subject.add(valid).should == 6
    end
  end

  [";", ",", "\n", "z", "+", ".", "*", "["].each do |sep|
    test = "//#{sep}\n" + [1,2,3].join(sep)
    it "should work using a custom delimiter (#{test.inspect})" do
      subject.add(test).should == 6
    end      
  end

  ["//;\n1;2;-3;-4;5;6", "1,2,-3,3,-4"].each do |neg|
    it "should raise an error when there are negatives (#{neg.inspect})" do
      expect { 
        subject.add(neg)
      }.to raise_error(StandardError, /negatives not allowed: -3,-4/)
    end
  end
  
  it "should ignore integers bigger than 1000" do
    subject.add("//;\n1;2;1000;1001;3").should == 1006
  end

  ["//[***]\n1***2***3", "//[**]\n1**2**3", "//[*]\n1*2*3"].each do |test|
    it "should support multiple-length delimiters (#{test.inspect})" do
      subject.add(test).should == 6
    end
  end

  ["//[;][,][z][.][*]\n1;2,3.4*5", "//[z][x][.]\n1.2z3x4x5"].each do |test|
    it "should support 1-char different delimiters (#{test.inspect})" do
      subject.add(test).should == 15
    end
  end

  [
    "//[**][,,,][;;;]\n1**2,,,3;;;4,,,5",
    "//[,,][*][zzz]\n1,,2*3*4zzz5",
    "//[**][..][++]\n1**2++3..4++5",
  ].each do |test|
    it "should support multiple-char different delimiters (#{test.inspect})" do
      subject.add(test).should == 15
    end
  end
  
end

