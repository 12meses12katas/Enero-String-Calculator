#!/usr/bin/env python
# -*- coding: utf-8 -*-

class Elements:
    def __init__(self, delimiter, value):
        self.delimiter = delimiter
        self.input = value

    def next(self):
        if not self.input:
            raise StopIteration

        token = self.__get_token_and_jump()
        return int(token)

    def __get_token_and_jump(self):
        length = self.__get_next_token_length()
        if not length:
            raise SyntaxError('Entrada inválida: ' + self.input )
        result = self.input[:length]
        self.input = self.input[length+len(self.delimiter):]
        return result

    def __get_next_token_length(self):
        result = 0
        while result < len(self.input):
            if self.__there_is_a_separator_at(result):
                break
            result += 1
        return result

    def __there_is_a_separator_at(self, pos):
        for delimiter in ('\n', self.delimiter):
            if self.input[pos:].startswith(delimiter):
                return True
        return False

class Separator:
    def __init__(self, value):
        self.value = value

    def __iter__(self):
        delimiter, value = self.__separe_statements()
        return Elements(delimiter, value)

    def __separe_statements(self):
        value = self.value
        delimiter = ','
        if self.__has_delimiter_specification():
            value = self.__separe_values ()
            delimiter = self.__separe_delimiter()
        return delimiter, value

    def __separe_values(self):
        pos = self.value.index('\n')
        return self.value[pos+1:]
            
    def __separe_delimiter(self):
        result = None
        if self.value[2] == '[':
            end = self.value.index(']')
            result = self.value[3:end]
        else:
            result = self.value[2]
        return result

    def __has_delimiter_specification(self):
        return len(self.value) > 2  and self.value[0] == '/' and self.value[1] == '/'
    
class StringCalculator:
    def __init__ (self):
        self.negatives = []
        self.sum = 0

    def add(self, number):
        for value in Separator(number):
            self.__process_number(value)
        self.__no_negatives_assertion()
        return self.sum

    def __no_negatives_assertion(self):
        if not len(self.negatives):
            return
        raise ValueError ("negatives not allowed:" + str(self.negatives))

    def __process_number(self, number):
        if number < 0:
            self.negatives.append(number)
        elif number < 1000:
            self.sum += number
        
