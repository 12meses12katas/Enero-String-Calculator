
require './StringCalculator'

describe "StringCalculator" do
	context "Cadena Vacia" do
		it "Retorna 0 si  parametro es vacio" do
			sc = StringCalculator.new
			sc.Add("").should == 0
		end
	end
end