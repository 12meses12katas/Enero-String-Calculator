require 'calculator'

describe Calculator do

   before(:each) do
      @calculator = Calculator.new
   end

   describe "empty_string_return_zero" do
      it "empty string expected 0" do
         @calculator.sum_of("").should == 0
      end
   end

   describe "string_with_one_number_five_returns_five" do
      it "5 expected" do
         @calculator.sum_of("5").should == 5
      end 
   end


   describe "string_with_one_number_one_returns_one" do
      it "1 expected" do
         @calculator.sum_of("1").should == 1
      end 
   end


   describe "string_with_one_number_ten_returns_one" do
      it "1 expected" do
         @calculator.sum_of("10").should == 1
      end 
   end

   describe "string_with_one_number_156_returns_12" do
      it "12 expected" do
         @calculator.sum_of("156").should == 12
      end 
   end


  

end
