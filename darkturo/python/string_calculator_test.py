import unittest
from random import randint, shuffle
from string_calculator import StringCalculator

class StringCalculatorTest(unittest.TestCase):
    def test_call_add_with_empty_string(self):
        calc = StringCalculator()
        self.assertEqual(0, calc.add(""))

    def test_call_add_with_one_number(self):
        calc = StringCalculator()
        self.assertEqual(1, calc.add("1"))

    def test_call_add_with_two_numbers(self):
        calc = StringCalculator()
        self.assertEqual(3, calc.add("1,2"))

    def test_call_add_with_an_arbitrary_number_of_numbers(self):
        calc = StringCalculator()
        sumIs55 = ",".join(map(lambda x: "%s" % x, range(1,11)))
        self.assertEqual(55, calc.add(sumIs55))

    def test_add_unknown_amount_of_numbers(self):
        calc = StringCalculator()
        theList = range(1, randint(10, 1000),1)
        shuffle(theList) 
        theStrLst = ",".join(map(lambda x: "%s" % x, theList)) 
        self.assertEqual(reduce(lambda x, y: x + y, theList), calc.add(theStrLst))

    def  test_add_allow_new_lines_between_numbers(self):
        calc = StringCalculator()
        self.assertEqual(6, calc.add("1\n2,3"))

    def test_add_supports_different_delimiters(self):
        calc = StringCalculator()
        self.assertEqual(3, calc.add("//;\n1;2"))

    def test_negative_number_will_throw_an_exception(self):
        calc = StringCalculator()
        try:
            calc.add("-1")
        except Exception, m:
            self.assertEqual("negatives not allowed: -1", m.message)
        else:
            self.fail()

    def test_multiple_negatives_will_throw_an_exception_with_all_of_them(self):
        calc = StringCalculator()
        try:
            calc.add("-1,-3,4,-5")
        except Exception, m:
            self.assertEqual("negatives not allowed: -1,-3,-5", m.message)
        else:
            self.fail()

    def test_numbers_bigger_than_1000_should_be_ignored(self):
        calc = StringCalculator()
        self.assertEqual(2, calc.add("2,1001"))

    def test_delimiters_can_be_of_any_length(self):
        calc = StringCalculator()
        self.assertEqual(6, calc.add("//[***]\n1***2***3"))

    def test_multiple_delimiters(self):
        calc = StringCalculator()
        self.assertEqual(6, calc.add("//[*][%]\n1*2%3"))

    def test_multiple_delimiters_of_any_length(self):
        calc = StringCalculator()
        self.assertEqual(15, calc.add("//[**][%=]\n1**2%=3**4**5"))


if (__name__ == "__main__"):
    unittest.main()
