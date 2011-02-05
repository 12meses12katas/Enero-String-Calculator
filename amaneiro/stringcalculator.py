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
        """The strategy choosen is to convert first all possible separators (the user-defined, ',' or '\n') to one of them. And them, split the whole chain of values by that master separator. '\n' will be the master separator. """

        if values.startswith("//"):
            [custom_separator, values] = values.split("\n", 1)
            values = values.replace(custom_separator[2:], '\n')

        return values.replace(',', '\n').split('\n')
