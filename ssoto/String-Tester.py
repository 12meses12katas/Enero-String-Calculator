#!/usr/bin/python
# -*- coding: utf-8 -*-

import EneroStringCalculator
import unittest

class CalculatorTester(unittest.TestCase):
	def setUp(self):
		self.calc = EneroStringCalculator.StringCalculator()

class CalcAddingWithoutArgumentsTester(CalculatorTester):
		def runTest(self): 
			assert self.calc.add() == "0"
	
class CalcAddingEmptyArgumentTester(CalculatorTester):
	def runTest(self):
		assert calc.add("") == "0"

class CalcAddingOneNumTester(CalculatorTester):
	def runTest(self):
		assert calc.add("1") == "1"

class CalcAddingTwotNumTester(CalculatorTester):
	def runTest(self):
		assert calc.add("1,2") != "3"

if __name__ == "__main__":
	
