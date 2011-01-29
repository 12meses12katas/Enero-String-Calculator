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

if __name__ == '__main__':
    unittest.main()
