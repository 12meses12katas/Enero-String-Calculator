require 'rspec'
require 'string_calculator.rb'

describe StringCalculator do
  it "returns 0 with an empty string" do
    subject.add("").should == 0
  end
  context "with one number" do
    it "returns 1 with 1" do
      subject.add("1").should == 1
    end
    it "returns 20 with 20" do
      subject.add("20").should == 20
    end
  end
  context "with more than one number" do
    it "returns 3 with 1,2" do
      subject.add("1,2").should == 3
    end
    it "returns 4 with 1,3" do
      subject.add("1,3").should == 4
    end
    it "returns 6 with 1,2,3" do
      subject.add("1,2,3").should == 6
    end
  end
  context "with \n as separator" do
    it "returns 7 when 3\n4" do
      subject.add("3\\n4").should == 7
    end
    it "returns 8 when 2,3\n3" do
      subject.add("2,3\\n3").should == 8
    end
  end
  context "with defined separators" do
    context "one character separator" do
      it "returns 9 with //;\n4;5" do
        subject.add("//;\\n4;5").should == 9
      end
    end
    context "more than one character separator" do
      it "should return 11 with //[aa]\\n5aa6" do
        subject.add("//[aa]\\n5aa6").should == 11
      end
    end
    context "more than one separator" do
      it "should return 12 with //[a][b]\\n1a2b9" do
        subject.add("//[a][b]\\n1a2b9").should == 12
      end
      it "should return 13 with //[aa][bb]\\n1aa3bb9" do
        subject.add("//[aa][bb]\\n1aa3bb9").should == 13
      end
    end
    context "valid numbers only from 0 to 1000" do
      it "should return 0 with 1001" do
        subject.add("1001").should == 0
      end
      it "raises with negative numbers" do
        expect{ subject.add("-1") }.to raise_error
      end
      it "Shows the negative numbers" do
        expect{ subject.add("-1,2,-3") }.to raise_error("negative numbers not allowed: -1,-3")
      end
    end
  end
end

