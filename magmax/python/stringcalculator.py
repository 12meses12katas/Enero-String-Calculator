#!/usr/bin/env python
# -*- coding: utf-8 -*-

class StringCalculator:
    def split_string (self, string):
        return string.split(',')

    def add(self, number):
        if not number:
            return 0
        result = 0
        for val in self.split_string(number):
            result += int(val)
        return result

    
    
