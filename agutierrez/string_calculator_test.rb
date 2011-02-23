require './string_calculator'

describe 'StringCalculator', '#add' do

  before(:each) do
    @kata = StringCalculator.new
  end

  Kata_Data = [
    ["Should return 0 with an empty string", "", 0],
    ["Should return an integer with a text number", "2", 2],
    ["Should return the sum of two numbers separated by a comma", "2,3", 5],
    ["Should accept \n as a valid separator", "2\n3,5", 10],
    ["Should accept custom char separator between \\ and \n", "\\**\n2,3**4", 9],
    ["Should ignore numbers greater than 1000", "2,1002,6", 8],
    ["Should allow multiple delimiters between []", "\\[**][;]\n2**;5;6", 13]
  ]

  Kata_Data.each do |kataItem|

    it kataItem[0] do
      @kata.add(kataItem[1]).should == kataItem[2]
    end

  end

  it "Should raise an error if there is a negative" do
    lambda{@kata.add("1,-2,4,-5")}.should raise_error "No negatives allowed: -2, -5"
  end

end