#!/usr/bin/python

import unittest
from stringcalculator import StringCalculator

class StringCalculatorTestCase(unittest.TestCase):

    def setUp(self):
        self.sc = StringCalculator()

    def testReturnZeroWhenNoParametersArePassed(self):
        self.assertEqual(self.sc.add(), 0)

    def testReturnSameValueIfOnlyOneParameterIsPassed(self):
        value = '1'
        self.assertEqual(self.sc.add(value), int(value))

    def testAddOperationIsOK(self):
        self.assertEquals(self.sc.add("1,2"), 3)

    def testAddOperationFails(self):
        self.assertNotEqual(self.sc.add("1,2"), 4)

    def testAddOperationWithNewLineSeparator(self):
        self.assertEquals(self.sc.add("1\n2"), 3)

    def testAddOperationIsOKWithBothTwoSeparators(self):
        self.assertEquals(self.sc.add("1,2\n3"), 6)

    def testAddOperationIsOKWithCustomSeparator(self):
        self.assertEquals(self.sc.add("//;\n4,5\n;6"), 15)

if __name__ == '__main__':
    unittest.main()
