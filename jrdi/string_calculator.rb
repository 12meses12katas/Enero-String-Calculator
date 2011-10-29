require 'rspec'

class StringCalculator
  
  def self.add(string)
    string.split(',').map(&:to_i).inject(0) {|total, number| total += number }
  end
  
end

describe 'StringCalculator.add' do
  it "should return 0 for ''" do
    StringCalculator.add('').should == 0
  end
  
  it "should return 2 for '2'" do
    StringCalculator.add('2').should == 2
  end
  
  it "should return 5 for '2,3'" do
    StringCalculator.add('2,3').should == 5
  end
  
  it "should return 5 for '2,3,4,5'" do
    StringCalculator.add('2,3,4,5').should == 14
  end
end