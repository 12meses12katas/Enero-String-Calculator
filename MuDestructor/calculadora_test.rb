
$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'calculadora'

class CalculadoraTest < Test::Unit::TestCase
  def test_add
    mi_calculo = Calculadora.new
    assert_equal(492, mi_calculo.add("258 234"))
    mi_calculo.reset()
    assert_equal(492, mi_calculo.add("258la%lal234"))
    mi_calculo.reset()
    assert_equal(492, mi_calculo.add("258\n234"))
    mi_calculo.reset()
    assert_equal(492, mi_calculo.add("258pppijfhyfh234"))
    mi_calculo.reset()
    assert_equal(492, mi_calculo.add("258 , 234"))
    mi_calculo.reset()
    assert_equal(817, mi_calculo.add("258;234 325"))
    mi_calculo.reset()
    assert_equal(559, mi_calculo.add("1258;234h325"))
    mi_calculo.reset()
  end
end
