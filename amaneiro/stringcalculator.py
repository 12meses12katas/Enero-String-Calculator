#!/usr/bin/python

class StringCalculator:

    def add(self, values=""):

        if (len(values) == 0):
            return 0

        sum=0
        for i in self.split_entry_values(values):
            sum=sum+int(i)
        return sum

    def split_entry_values(self, values):
        return values.replace(',', '\n').split('\n')
