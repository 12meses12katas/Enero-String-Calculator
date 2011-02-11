#!/usr/bin/python

import unittest
from add import add

class TestAdd(unittest.TestCase):
    
    def test_empty(self):
        self.assertEqual(0, add())
        self.assertEqual(0, add(""))
        self.assertEqual(0, add(None))

    def test_one(self):
        self.assertEqual(2, add("2"))

    def test_two(self):
        self.assertEqual(3, add("1,2"))

    def test_many(self):
        self.assertEqual(10, add("1,2,3,4"))

    def test_newline(self):
        self.assertEqual(10, add("1,2\n3\n4"))

    def test_delimiter(self):
        self.assertEqual(10, add("\\;\n1;2\n3;4"))

if __name__ == '__main__':
    unittest.main()

