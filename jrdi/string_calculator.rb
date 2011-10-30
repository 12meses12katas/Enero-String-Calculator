require 'rspec'

class StringCalculator
  
  def self.add(string)    
    
    delimiters = if delimiter = (string.match(%r{^\/\/\[(.+)\]\\n}) && $1)
      delimiter.split('][')
    else
      [',']
    end
    
    numbers = string.split(%r{#{delimiters + ['\n']}}).map(&:to_i).delete_if {|i| i > 1000}
    
    negatives = numbers.select {|number| number < 0}
    raise "Negatives numbers not allowed: #{negatives.join(', ')}" if negatives.any?
    
    return 0 if numbers.empty?
    numbers.reduce(:+)
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
  
  it "should return 14 for '2,3,4,5'" do
    StringCalculator.add('2,3,4,5').should == 14
  end
  
  it "should return 6 for '1\n2,3'" do
    StringCalculator.add('1\n2,3').should == 6
  end
  
  it "should return 3 for '//[;]\n1;2'" do
    StringCalculator.add('//[;]\n1;2').should == 3
  end
  
  it "should raise an exception when called with a negative number" do
    -> { StringCalculator.add("1,2,-3,5,-7") }.should raise_error("Negatives numbers not allowed: -3, -7")
  end
  
  it "should return 2 for '2,1001'" do
    StringCalculator.add('2,1001').should == 2
  end
  
  it "should return 3 for '//[;;;]\n1;;;2'" do
    StringCalculator.add('//[;;;]\n1;;;2').should == 3
  end
  
  it "should return 7 for '//[;;;][***]\n1;;;2***4'" do
    StringCalculator.add('//[;;;][***]\n1;;;2***4').should == 7
  end
end