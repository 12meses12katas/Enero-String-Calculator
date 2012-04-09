
require './StringCalculator'

describe "StringCalculator" do
	
	context "Cadena Vacia" do
		it "Retorna 0 si  parametro es vacio" do
			sc = StringCalculator.new
			sc.Add("").should == 0
		end
	end

	context "Para un Numero" do
		it "Retorna el numero cero" do
			sc = StringCalculator.new
			sc.Add("0").should == 0
		end
		it "Retorna el numero uno" do
			sc = StringCalculator.new
			sc.Add("1").should == 1
		end
		it "Retorna el numero 123456" do
			sc = StringCalculator.new
			sc.Add("123456").should == 123456
		end
	end

	context "Dos o mas numeros" do
		it "Retorna 1+2=3" do
			sc = StringCalculator.new
			sc.Add("1,2").should == 3
		end
		it "Trabaja con 10 numeros" do
			sc = StringCalculator.new
			sc.Add("1,2,3,4,5,6,7,8,9,10")
		end
	end
end