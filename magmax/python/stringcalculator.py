#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

class StringCalculator:
    def __init__ (self):
        self.input = None
        self.separator = ','

    def add(self, number):
        if not number:
            return 0
        self.input = number
        result = 0
        for val in self.__split_string():
            result += int(val)
        return result

    def __split_string (self):
        self.__extract_separator()
        self.__validate()
        return re.findall('(\d+)[,|\n]?', self.input)

    def __extract_separator(self):
        pattern = re.compile('^//(.)\n')
        match = re.match(pattern, self.input)
        if match:
            self.separator = match.groups()[0]

    def __validate(self):
        pattern = re.compile('^(//.\n)?(\d+[\n|%s])*(\d+)$'%self.separator)
        if not re.search(pattern, self.input):
            raise SyntaxError("invalid input")
    
