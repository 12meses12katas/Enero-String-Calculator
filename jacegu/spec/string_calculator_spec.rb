require 'spec_helper'

RSpec::Matchers.define :add do |expected|
  match do |string|
    @result = StringCalculator::Calculator.new.add(string)
    @result == expected
  end

  failure_message_for_should do |string|
    "\"#{string}\" should add #{expected} but added #{@result}"
  end
end

describe StringCalculator do
  context 'with empty string' do
    it 'returns 0 for ""' do
      ''.should add 0
    end
  end

  context 'with one number' do
    it 'returns 0 for "0"' do
      '0'.should add 0
    end

    it 'returns 1 for "1"' do
      '1'.should add 1
    end

    it 'returns 255 for "255"' do
      '255'.should add 255
    end
  end

  context 'with two numbers' do
    it 'returns 3 for "1,2"' do
      '1,2'.should add 3
    end

    it 'returns 5 for "2,3"' do
      '2,3'.should add 5
    end

    it 'returns 17 for "7,10"' do
      '7,10'.should add 17
    end

    it 'returns 19 for "10,9"' do
      '10,9'.should add 19
    end
  end

  context 'with more than two numbers' do
    it 'returns 6 for "1,2,3"' do
      '1,2,3'.should add 6
    end
    
    it 'returns 8 for "2,2,2,2"' do
      "2,2,2,2".should add 8
    end
  end

  context 'with new line as delimiter' do
    it 'returns 3 for "1\n2"' do
      "1\n2".should add 3
    end

    it 'returns 5 for "2\n3"' do
      "2\n3".should add 5
    end

    it 'returns 6 for "1\n2,3"' do
      "1\n2,3".should add 6
    end

    it 'returns 77 for "50,20\n7"' do
      "50,20\n7".should add 77
    end
  end

  context 'with custom delimiter' do
    it 'returns 3 for "//;\n1;2"' do
      "//;\n1;2".should add 3
    end

    it 'returns 16 for "//+\n4+4+4+4"' do
      "//+\n4+4+4+4".should add 16
    end

    it 'returns 25 for "//.\n10.5.10"' do
      "//.\n10.5.10".should add 25
    end

    it 'returns 107 for "//-\n100-7"' do
      "//-\n100-7".should add 107 
    end

    it 'returns 20 for "//?\n5,5\n5?5"' do
      "//?\n5,5\n5?5".should add 20
    end

    it 'returns 8 for "//[\n4[4"' do
      "//[\n4[4".should add 8
    end
  end

  context 'with negative numbers' do
    let(:calculator){ StringCalculator::Calculator.new }

    it 'raises error' do
      expect{ calculator.add('-1') }.to raise_error
    end

    it 'shows negative numbers in error message' do
      expect{ calculator.add('-8, 10, -12') }.to raise_error "negatives not allowed: -8, -12"
    end

  end

  context 'with numbers equal or above 1000' do
    it 'returns 2 for "1001,2"' do
      '1001,2'.should add 2
    end

    it 'returns 1000 for "1000"' do
      '1000'.should add 1000
    end
  end

  context 'with multiple char custom delimiters' do
    it 'returns 6 for "//[***]\n1***2***3"' do
      "//[***]\n1***2***3".should add 6
    end

    it 'returns 10 for "//[++]\n6++4"' do
      "//[++]\n6++4".should add 10
    end
  end

  context 'with more than one delimiter' do
    it 'returns 12 for "//[+][*]\n4+4*4"' do
      "//[+][*]\n4+4*4".should add 12
    end

    it 'returns 125 for "//[+][$$][...]\n100+10$$10...5"' do
      "//[+][$$][...]\n100+10$$10...5".should add 125
    end
  end

end
