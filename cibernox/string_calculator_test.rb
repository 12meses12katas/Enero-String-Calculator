require "string_calculator.rb"
require "test/unit"

class TestStringCalculator < Test::Unit::TestCase
  @@calc = StringCalculator.new
  # Si le metes un "" o nil, lo toma como un 0
  def test_empty_string_is_zero
    assert_equal 0, @@calc.add()
    assert_equal 0, @@calc.add("")
  end

  # Si le metes un string con un solo número, devueve ese número
  def test_one_number_sum
    assert_equal 7, @@calc.add('7')
    assert_equal 1, @@calc.add('1')
  end

  # Si le metes 2 o 3 números separados por comas, los separa y los suma
  def test_simple_sum
    assert_equal 10, @@calc.add("2,5,3")
    assert_equal 11, @@calc.add("6,5")
  end
  # Si le metes un número indeterminado de números separados por comas, los suma
  def test_multiple_arguments_sum
    assert_equal(5050, @@calc.add("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16," +
                         "17,18,19,20,21,22,23,24,25,26,27,28,29," +
                         "30,31,32,33,34,35,36,37,38,39,40,41,42," +
                         "43,44,45,46,47,48,49,50,51,52,53,54,55," +
                         "56,57,58,59,60,61,62,63,64,65,66,67,68," +
                         "69,70,71,72,73,74,75,76,77,78,79,80,81," +
                         "82,83,84,85,86,87,88,89,90,91,92,93,94," +
                         "95,96,97,98,99,100"));
  end 
  # Si un string contiene un salto de línea, se actúa como separador
  def test_newline_as_delimiter
    assert_equal(15, @@calc.add("4,\n5,6"))    
    assert_equal(15, @@calc.add("4,\n5,6\n1003"))    
  end
  # Si le pasas un número mayor de 1000, lo ignora
  def test_reject_greater_than_1000
    assert_equal(0, @@calc.add("1008"))
    assert_equal(7, @@calc.add("1001,7"))
  end
  # Si le pasas un negativo, lo ignora
  def test_reject_negatives
    assert_raise( NegativeNumbersNotAllowed ) { @@calc.add("-7") }
    assert_raise( NegativeNumbersNotAllowed ) { @@calc.add("-7,-4,-1") }
    assert_raise( NegativeNumbersNotAllowed ) { @@calc.add("3,-4,1") }
  end
  # Si le pasas un delimitador siguien el formato "//[delimiter]\n[numbers...]", debe funcionar 
  def test_custom_delimiter
    assert_equal(0, @@calc.add("//[%]\n1008"))
    assert_equal(12, @@calc.add("//[%]\n1008%4%8"))
    assert_equal(20, @@calc.add("//[%]\n8%3%8\n1"))
  end
  # El delimitador puede tener varios caracteres
  def test_custom_multichar_delimiter
    assert_equal(20, @@calc.add("//[caca]\n8caca3caca8\n1"))
    assert_equal(17, @@calc.add("//[caca]\n8caca1003caca8\n1"))
    assert_equal(21, @@calc.add("//[caca]\n8\n1003caca8\n5"))
  end
  # El pueden definise y usarse varios delimitadores, definiendolo con el formato "//[del2][del2]\n[numbers...]"
  def test_multiple_custom_delimiters
    assert_equal(20, @@calc.add("//[%][;]\n8;3%8\n1"))
    assert_equal(17, @@calc.add("//[%][;]\n8;1003%8\n1"))
    assert_equal(28, @@calc.add("//[%][;]\n8\n1003%8\n12"))
  end
  # Pueden definirse y usarse varios delimitadores multicaracter
  def test_multiple_custom_multichar_delimiters
    assert_equal(20, @@calc.add("//[caca][pedo]\n8caca3pedo8\n1"))
    assert_equal(17, @@calc.add("//[caca][pedo]\n8caca1003caca8\n1"))
    assert_equal(19, @@calc.add("//[caca][pedo][%%][;]\n8\n1003%%1;pedo8\n2"))
  end
  
end