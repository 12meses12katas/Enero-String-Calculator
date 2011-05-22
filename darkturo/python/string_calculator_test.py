import unittest
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

if (__name__ == "__main__"):
    unittest.main()
