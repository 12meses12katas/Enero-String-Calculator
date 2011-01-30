require './string_calculator'

describe StringCalculator do

   before(:each) do
      @calculator = StringCalculator.new
   end

   describe "empty_string_return_0" do
      it "returns 0 if the string is empty" do
         @calculator.calculate(nil).should == 0
         @calculator.calculate("").should == 0
      end
   end

  describe "string_with_one_number_return_that_number" do
      it "returns 5 if the string is '5'" do
	 @calculator.calculate("5").should == 5
      end
  end
  
  describe "string_with_two_number_return_a_sum_of_those_numbers" do
      it "returns 12 if the string is '7,5'" do
	 @calculator.calculate("7,5").should == 12 
      end
  end

  describe "string_with_few_numbers_return_a_sum_of_those_numbers" do
      it "returns 24 if string is '3,5,10,6'" do
	 @calculator.calculate("3,5,10,6").should == 24
      end
  end

  describe "string_with_new_lines_return_a_sum_of_the_numbers" do
      it "return 12 if string is '7\n2,3'" do
         @calculator.calculate("7\n2,3").should == 12
      end
  end 

  describe "string_with_header_can_change_the_separator" do
      it "return 15 if string is '//;\\n5;4;6'" do
	 @calculator.calculate("//;\\n5;4;6").should == 15
      end
  end
end
