#!/usr/bin/env python
# -*- coding: utf-8 -*-

class Elements:
    def __init__(self, delimiter, value):
        self.delimiter = delimiter
        self.input = value

    def next(self):
        if not self.input:
            raise StopIteration

        result = self.__get_token_and_jump()
        return self.__invalidate_big_numbers(result)

    def __invalidate_big_numbers(self, value):
        result = int(value)
        if result > 1000: 
            return 0
        return result

    def __get_token_and_jump(self):
        length = self.__get_next_token_length()
        if not length:
            raise SyntaxError('Entrada inválida: ' + self.input )
        result = self.input[:length]
        self.input = self.input[length+1:]
        return result

    def __get_next_token_length(self):
        result = 0
        for char in self.input:
            if self.__is_delimiter(char):
                break
            result += 1
        return result

    def __is_delimiter(self, value):
        return value in (self.delimiter, '\n')

class Separator:
    def __init__(self, value):
        self.value = value

    def __iter__(self):
        delimiter, value = self.__separe_statements()
        return Elements(delimiter, value)

    def __separe_statements(self):
        if self.__has_delimiter_specification():
            return self.value[2], self.value[4:]
        return ',',self.value

    def __has_delimiter_specification(self):
        return self.value and self.value[0] == '/'
    
class StringCalculator:
    def add(self, number):
        result = 0
        for value in Separator(number):
            result += value
        return result
