import re
from nose.tools import *

class StringCalculator:
    def add(self,s):
        if s == "": return 0
        (delimiter,st) = self.__find_delimiter(s)
        numbers = [int(x) for x in re.split(delimiter, st)]
        negatives = [x for x in numbers if x < 0] #collect the negatives to show them after
        if negatives:
            raise Exception("Tried to add negatives: %s" % negatives)
        to_sum = [x for x in numbers if x <= 1000]
        return sum(to_sum)

    def __find_delimiter(self,s):
        pattern = "^//(\[.+\])+\n"
        scan = re.search(pattern, s)
        if scan: #find out if we have custom delimiters
            #we make a regexp with delimiters: (ex) *|,|??
            delimiters = "|".join([ re.escape(dl) for dl in re.findall("\[([^\[\]]+)\]",
                scan.group(1)) ])
            return delimiters, re.sub(pattern,"",s)
        return ",|\n",s

class TestStringCalculator:
    def setUp(self):
        self.s = StringCalculator()

    def test_empty(self):
        assert self.s.add("") == 0

    def test_simple(self):
        assert self.s.add("1,2,3") == 6

    def test_with_carriage(self):
        assert self.s.add("1,2\n3") == 6

    def test_big_stuff(self):
        assert self.s.add("1"+",1"*1000) == 1001

    def test_custom_delimiters(self):
        assert self.s.add("//[o][hai]\n1o2o34hai0") == 37

    def test_custom_special_delimiters(self):
        assert self.s.add("//[??][*]\n1*2*34??0") == 37

    def test_negatives(self):
        assert_raises(Exception, self.s.add, "//*\n-1*-1*3")

    def test_no_more_than_one_thousand(self):
        assert self.s.add("1,2,3,1001") == 6
