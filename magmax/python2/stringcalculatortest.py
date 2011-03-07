#!/usr/bin/env python
# -*- coding: utf-8 -*-

import unittest
import re
from stringcalculator import StringCalculator

class StringCalculatorTest (unittest.TestCase):
    def setUp(self):
        self.sut = StringCalculator()

    def assertRaisesRegExp (self, excClass, regexp, callableObj, *args, **kargs):
        try:
            callableObj(*args, **kargs)
        except excClass as e:
            if not re.match(regexp, str(e)):
                self.fail("Invalid message: \n\t" + str(e) + '\nExpected:\n\t' + regexp)
            
        else:
            if hasattr(excClass,'__name__'): excName = excClass.__name__
            else: excName = str(excClass)
            raise self.failureException, "%s not raised" % excName


    def test_empty_string(self):
        self.assertEqual(0, self.sut.add(""))

    def test_only_one_number(self):
        self.assertEqual(1, self.sut.add("1"))

    def test_only_one_number_bigger (self):
        self.assertEqual(123, self.sut.add("123"))

    def test_two_numbers(self):
        self.assertEqual(4, self.sut.add("1,3"))

    def test_two_numbers_bigger(self):
        self.assertEqual(200, self.sut.add("150,50"))

    def test_tree_numbers (self):
        self.assertEqual(300, self.sut.add("75,75,150"))

    def test_enter_is_separator(self):
        self.assertEqual(6, self.sut.add("3,2\n1"))

    def test_enter_is_separator_bigger_numbers(self):
        self.assertEqual(600, self.sut.add("300,200\n100"))

    def test_change_separator(self):
        self.assertEqual(6, self.sut.add("//;\n4;2"))

    def test_change_separator_with_enter(self):
        self.assertEqual(10, self.sut.add("//;\n5;3\n2"))

    def test_invalid_separator(self):
        self.assertRaises(ValueError, self.sut.add, "//;\n5,4")

    def test_ignore_big_values(self):
        self.assertEqual(2, self.sut.add("2,1002"))

    def test_multicharacter_delimiter (self):
        self.assertEqual(6, self.sut.add("//[***]\n3***2***1"))

    def test_invalid_negatives(self):
        self.assertRaisesRegExp(ValueError, "negatives not allowed:\[-2]", self.sut.add, "-2")
        
    def test_invalid_negatives_plural(self):
        self.assertRaisesRegExp(ValueError, "negatives not allowed:\[-2, -3]", self.sut.add, "-2,-3")

if __name__ == '__main__':
    unittest.main()
