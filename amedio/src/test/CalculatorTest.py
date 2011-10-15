import unittest
from src.main import Calculator


class CalculatorTest(unittest.TestCase):

    def testAddNoParams(self):
        
        calc = Calculator.Calculator()
        
        expected = 0
        resulted = calc.add("")
        
        self.assertEquals(expected, resulted, "Adding no params Error")
        
        pass
        
    def testAddOneParam(self):
        
        calc = Calculator.Calculator()
        
        expected = 1
        resulted = calc.add("1")
        
        self.assertEquals(expected, resulted, "Adding one param Error")
        
        pass


if __name__ == "__main__":
    unittest.main()