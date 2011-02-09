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

  def test_multiple_numbers
    assert_equal(5050, add("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16," +
                           "17,18,19,20,21,22,23,24,25,26,27,28,29," +
                           "30,31,32,33,34,35,36,37,38,39,40,41,42," +
                           "43,44,45,46,47,48,49,50,51,52,53,54,55," +
                           "56,57,58,59,60,61,62,63,64,65,66,67,68," +
                           "69,70,71,72,73,74,75,76,77,78,79,80,81," +
                           "82,83,84,85,86,87,88,89,90,91,92,93,94," +
                           "95,96,97,98,99,100"));
  end

  def test_newlines
    assert_equal(6, add("1\n2,3"))
  end

  def test_custom_delimiter
    assert_equal(6, add("//;\n1\n2;3"))
  end

  def test_reject_negatives
    assert_raises(ArgumentError) { add("1,3,-1,4") }
  end

  def test_ignore_greater_than_1000
    assert_equal(2, add("1001,2"))
    assert_equal(1002, add("1000,2"))
  end

  def test_multicharacter_delimiter
    assert_equal(9, add("//[***]\n1\n2***3***3"))
  end

  def test_multicharacter_multiple_delimiter
    assert_equal(9, add("//[***][%]\n1\n2***3%3"))
  end
end

