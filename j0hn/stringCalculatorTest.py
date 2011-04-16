#!/usr/bin/env python
# -*- encodign: utf-8 -+-

import unittest
from stringCalculator import StringCalculator

SUMS = {"5": 5,
        "2,2": 4,
        "3,4": 7,
        "10,11": 21,
        "13,37": 50}


class StringCalculatorTest(unittest.TestCase):

    def setUp(self):
        self.calc = StringCalculator()

    def test_empty(self):
        result = self.calc.add("")
        self.assertEqual(result, 0)

    def test_one_number(self):
        result = self.calc.add("1")
        self.assertEqual(result, 1)

    def test_two_numbers(self):
        result = self.calc.add("1,2")
        self.assertEqual(result, 3)

    def test_known_sums(self):
        for key in SUMS.keys():
            result = self.calc.add(key)
            self.assertEqual(result, SUMS[key])

    def test_new_lines(self):
        result = self.calc.add("1\n4")
        self.assertEqual(result, 5)

    def test_new_lines_and_comas(self):
        result = self.calc.add("1\n2,3")
        self.assertEqual(result, 6)

    def test_invalid_input_1(self):
        self.assertRaises(ValueError, self.calc.add, "1\n,3")

    def test_invalid_input_2(self):
        self.assertRaises(ValueError, self.calc.add, "5,6,7\n,3,7")

    def test_different_delimiter_1(self):
        result = self.calc.add("//;\n5;5;8")
        self.assertEqual(result, 18)

    def test_different_delimiter_2(self):
        result = self.calc.add("//|\n5|2\n7|1\n5")
        self.assertEqual(result, 20)

    def test_different_delimiter_3(self):
        result = self.calc.add("//[soplete]\n5soplete2soplete3\n1soplete1")
        self.assertEqual(result, 12)

    def test_invalid_delimiter_1(self):
        self.assertRaises(ValueError, self.calc.add, "//\n1,2,3")

    def test_invalid_delimiter_2(self):
        self.assertRaises(ValueError, self.calc.add, "//[**\n1**2")

    def test_negative_number(self):
        self.assertRaises(ValueError, self.calc.add, "1,2,-3")

    def test_negative_number_message(self):
        try:
            result = self.calc.add("1,2,-3")
        except ValueError, exception:
            self.assertTrue("-3" in exception.message)

    def test_ignore_big_nums(self):
        result = self.calc.add("2, 1001")
        self.assertEqual(result, 2)

if __name__ == "__main__":
    unittest.main()
