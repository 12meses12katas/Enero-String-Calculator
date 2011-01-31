#!/usr/bin/python
# -*- encoding: utf-8 -*-
#
# author: javi santana


# pip install nose
# nostest add.py


import re

def add(s):
    """ add numbers """
    numbers = [int(x) for x in re.findall('(-?\d+)', s)]
    negatives = [x for x in numbers if x < 0]
    if negatives:
        raise Exception("negative numbers not allowed: %s" % negatives)
    return sum(numbers)


from nose.tools import *
def test_add():
    assert add("1,2") == 3
    assert add("1,2,3") == 6
    assert add("") == 0
    assert add("1,"*100) == 100 # big ammount of numbers :P
    assert add("1\n2,3") == 6
    assert add("//;\n1;2") == 3
    assert_raises(Exception, add, "-1,2")
