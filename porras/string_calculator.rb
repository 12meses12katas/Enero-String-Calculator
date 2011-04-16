require 'rspec'

class StringCalculator
  def self.add(string)
    separator = string.match(%r{^//(\[(.+)\])+}) && $1
    numbers = string.split(%r{[\,\n#{separator}]}).map(&:to_i).reject { |i| i > 1000 }
    
    negatives = numbers.select { |i| i < 0 }
    raise("negatives not allowed: #{negatives.join(", ")}") if negatives.any?
    
    numbers.inject(0) { |total, number| total += number }
  end
end

describe "StringCalculator.add" do
  TESTCASES = {
    "" => 0,
    "1" => 1,
    "2" => 2,
    "1,1" => 2,
    "2,3" => 5,
    "17,21" => 38,
    "1,2,3,4,5,6" => 21,
    "1\n2,3" => 6,
    "//[;]\n1;2" => 3,
    "//[@]\n2@3@5" => 10,
    "2,1000" => 1002,
    "2,1001" => 2,
    "//[***]\n1***2" => 3,
    "//[;][@]\n1;2@3" => 6,
    "//[abc][def]\n1abc2def3" => 6
  }
  TESTCASES.each do |string, i|
    it "should return #{i} for '#{string}'" do
      StringCalculator.add(string).should == i
    end
  end
  
  it "should raise an exception when called with a negative number" do
    lambda { StringCalculator.add("1,2,-3,5,-7")}.should raise_error("negatives not allowed: -3, -7")
  end
end