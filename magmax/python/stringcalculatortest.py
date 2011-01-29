#!/usr/bin/env python
# -*- coding: utf-8 -*-

import unittest
from stringcalculator import StringCalculator

class StringCalculatorTest (unittest.TestCase):
    def setUp(self):
        self.sut = StringCalculator()

    def test_empty_string(self):
        self.assertEqual(0, self.sut.add(""))

    def test_string_with_a_number(self):
        self.assertEqual(1, self.sut.add("1"))

    def test_string_with_a_long_number(self):
        self.assertEqual(123456789, self.sut.add("123456789"))

    def test_two_numbers(self):
        self.assertEqual(2, self.sut.add("1,1"))

    def test_two_large_numbers(self):
        self.assertEqual(999999999, self.sut.add("123456789,876543210"))

    def test_more_numbers(self):
        self.assertEqual(25, self.sut.add("1,10,4,5,3,2"))
    

if __name__ == '__main__':
    unittest.main()
