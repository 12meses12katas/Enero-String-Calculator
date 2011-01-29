#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

class StringCalculator:
    def add(self, number):
        if not number:
            return 0
        result = 0
        for val in self.__split_string(number):
            result += int(val)
        return result

    def __split_string (self, string):
        self.__validate(string, ',')
        return re.findall('(\d+)[,|\n]?', string)

    def __validate(self, string, separator):
        pattern = re.compile('^(//.\n)?(\d+[\n|%s])*(\d+)$'%separator)
        if not re.search(pattern, string):
            raise SyntaxError("invalid input")
    
