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
            result += self.__correct_number(val)
        return result

    def __correct_number (self, value):
        number = int(value)
        if number > 1000:
            return 0
        return number

    def __split_string (self):
        self.__extract_separator()
        self.__validate()
        return re.findall(self.PATTERN_NUMBERS, self.input)

    def __extract_separator(self):
        match = re.match(self.PATTERN_SEPARATOR, self.input)
        if not match:
            return
        for group in match.groups():
            if group:
                self.separator = group

    def __validate(self):
        pattern = re.compile(self.PATTERN_VALIDATION)
        print self.PATTERN_VALIDATION.replace('\n','\\n')
        if not re.search(pattern, self.input):
            raise SyntaxError("invalid input")
    


    def __pattern_numbers(self):
        return '(\d+)[(?:%s)|\n]?' % self.separator

    def __pattern_separator(self):
        simple   = '//(.)\n'
        multiple = '//\[(.+)\]\n'
        return '^(?:%s)|(?:%s)' % (simple, multiple)

    def __pattern_validation(self):
        return '^(%s)?(%s)*\d+$' % (self.PATTERN_SEPARATOR, self.PATTERN_NUMBERS)

    PATTERN_VALIDATION = property(__pattern_validation)
    PATTERN_SEPARATOR  = property(__pattern_separator)
    PATTERN_NUMBERS    = property(__pattern_numbers)
