require "calculator"
require "test/unit"

class TestCalculator < Test::Unit::TestCase
  def test_empty_is_0
    assert_equal(0, add(""))
  end

  def test_simple_addition
    assert_equal(2, add("1,1"))
    assert_equal(7, add("1,4,2"))
  end
end
