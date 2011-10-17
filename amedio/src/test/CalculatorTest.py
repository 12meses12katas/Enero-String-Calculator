import unittest
from src.main.calculator import Calculator


class CalculatorTest(unittest.TestCase):

    def testAddNoParams(self):
        
        calc = Calculator()
        
        expected = 0
        resulted = calc.add("")
        
        self.assertEquals(expected, resulted, "Adding no params Error")
        
        pass
        
    def testAddOneParam(self):
        
        calc = Calculator()
        
        expected = 1
        resulted = calc.add("1")
        
        self.assertEquals(expected, resulted, "Adding one param Error")
        
        expected = 2
        resulted = calc.add("2")
        
        self.assertEquals(expected, resulted, "Adding one param Error")
        
        expected = 3
        resulted = calc.add("3")
        
        self.assertEquals(expected, resulted, "Adding one param Error")
        
        pass
    
    def testAddTwoParams(self):
        
        calc = Calculator()
        
        expected = 2
        resulted = calc.add("1,1")
        
        self.assertEquals(expected, resulted, "Adding two params Error")
        
        expected = 3
        resulted = calc.add("1,2")
        
        self.assertEquals(expected, resulted, "Adding two params Error")
        
        expected = 1
        resulted = calc.add("0,1")
        
        self.assertEquals(expected, resulted, "Adding two params Error")
        
        expected = 5
        resulted = calc.add("3,2")
        
        self.assertEquals(expected, resulted, "Adding two params Error")
        
        expected = 20
        resulted = calc.add("10,10")
        
        self.assertEquals(expected, resulted, "Adding two params Error")
        
        pass

    def testAddSomeParams(self):
        
        calc = Calculator()
        
        expected = 6
        resulted = calc.add("1,2,3")
        
        self.assertEquals(expected, resulted, "Adding some params Error")
        
        expected = 12
        resulted = calc.add("1,2,3,6")
        
        self.assertEquals(expected, resulted, "Adding some params Error")
        
        expected = 5
        resulted = calc.add("1,1,3")
        
        self.assertEquals(expected, resulted, "Adding some params Error")
        
        expected = 30
        resulted = calc.add("10,10,10")
        
        self.assertEquals(expected, resulted, "Adding some params Error")
        
        pass

if __name__ == "__main__":
    unittest.main()