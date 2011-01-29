#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

class StringCalculator:
    def __split_string (self, string):
        return re.findall('(\d+)[,|\n]?', string)

    def add(self, number):
        if not number:
            return 0
        result = 0
        for val in self.__split_string(number):
            result += int(val)
        return result

    
    
