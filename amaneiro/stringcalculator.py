#!/usr/bin/python

class StringCalculator:

    def Add(self, values=""):

        if (len(values) == 0):
            return 0

        sum=0
        for i in values.replace(',', '\n').split('\n'):
            sum=sum+int(i)
        return sum
