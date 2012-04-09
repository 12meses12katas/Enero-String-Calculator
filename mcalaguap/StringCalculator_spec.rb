
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

	context "Salto de linea en lugar de , para separar los numeros" do
		it "Retorna 20+20=40" do
			sc = StringCalculator.new
			sc.Add("20\n20").should == 40
		end
	end

	context "Cualquier delimitador" do
		it "Trabaja con ; y retrona 40" do
			sc = StringCalculator.new
			sc.Add("//;\n20;20").should == 40
		end
		it "Trabaja con culquiera como el caracter H" do
			sc = StringCalculator.new
			sc.Add("//H\n20H20").should == 40
		end
	end

	context "Numero negativos no permitidos" do
		it "Saltar excepcion con un numero negativo" do
			sc = StringCalculator.new
			expect{ sc.Add("//;\n20;-20") }.to raise_error "negative numbers are not allowed -20"
		end
		it "Saltar excepcion con N numeros negativo" do
			sc = StringCalculator.new
			expect{ sc.Add("//;\n1;-2;3;-4;5;-6;7;-8;9;-10") }.to raise_error "negative numbers are not allowed -2,-4,-6,-8,-10"
		end
	end

end