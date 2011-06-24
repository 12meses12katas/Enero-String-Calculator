import unittest
from StringCalculator import StringCalculator

class TestStringCalculator(unittest.TestCase):
    def setUp(self):
        self.calc = StringCalculator()

    def testVacio(self):
        self.assertEqual(0, self.calc.Add(""))

    def testUnNumero(self):
        self.assertEqual(1, self.calc.Add("1"))
        self.assertEqual(2, self.calc.Add("2"))

    def testDosNumeros(self):
        self.assertEqual(3, self.calc.Add("1,2"))
        self.assertEqual(30, self.calc.Add("10,20"))

    def testVariosNumeros(self):
        self.assertEqual(6, self.calc.Add("1,2,3"))
        self.assertEqual(60, self.calc.Add("10,20,30")) 
        
    def testSeparadorSaltoLinea(self):
        self.assertEqual(6, self.calc.Add("1\n2,3"))

    def testNoSeAdmiteSeparadorDoble(self):
        self.assertEqual(6, self.calc.Add("1\n,,3"))

if __name__== "main":
    unittest.main()
