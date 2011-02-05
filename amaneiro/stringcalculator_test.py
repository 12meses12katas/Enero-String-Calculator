#!/usr/bin/python

import unittest
from stringcalculator import StringCalculator

class StringCalculatorTests(unittest.TestCase):

    def testReturnZeroWhenNoParametersArePassed(self):
        sc = StringCalculator()
        self.assertEqual(sc.Add(), 0)

    def testReturnSameValueIfOnlyOneParameterIsPassed(self):
        sc = StringCalculator()
        value = '1'
        self.assertEqual(sc.Add(value), int(value))

    def testAddOperationIsOK(self):
        sc = StringCalculator()
        self.assertEquals(sc.Add("1,2"), 3)

    def testAddOperationFails(self):
        sc = StringCalculator()
        self.assertNotEqual(sc.Add("1,2"), 4)

    def testAddOperationWithNewLineSeparator(self):
        sc = StringCalculator()
        self.assertEquals(sc.Add("1\n2"), 3)

    def testAddOperationIsOKWithBothTwoSeparators(self):
        sc = StringCalculator()
        self.assertEquals(sc.Add("1,2\n3"), 6)

if __name__ == '__main__':
    unittest.main()

