import StringCalculator
import unittest

class TestStringCalculator(unittest.TestCase):
	def setUp(self):
		self.calculator = StringCalculator.StringCalculator()

	def test_zeroArgs(self):
		self.assertTrue(self.calculator.add()==0,"0 args call error")
	def test_blankArg(self):
                self.assertTrue(self.calculator.add("")==0,"Blank arg call error")
	def test_oneArg(self):
                self.assertTrue(self.calculator.add("1")==1,"1 arg call error")
	def test_twoArgs(self):
                self.assertTrue(self.calculator.add("1,2")==3,"2 args call error")


if __name__ == "__main__":
    unittest.main()
