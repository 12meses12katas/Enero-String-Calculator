#!/usr/bin/python

#my own exceptions
class NegativesNotAllowed(Exception): pass

class StringCalculator:

    def add(self, values=""):

        if (len(values) == 0):
            return 0

        adapter = EntryValuesAdapter(values)
        if (adapter.getNumberOfValues() == 0):
            return 0

        if (not adapter.hasSomeNegative()):
            sum=0
            for i in adapter.getValues():
                sum=sum+int(i)
            return sum
        else:
            raise NegativesNotAllowed()

class EntryValuesAdapter:

    def __init__(self, values):
        self.has_negative = False
        self.adapted_values = self.parse(values)

    def getNumberOfValues(self):
        return len(self.adapted_values)

    def hasSomeNegative(self):
        if (int(min(self.adapted_values)) < 0):
            return True
        else:
            return False

    def getValues(self):
        return self.adapted_values

    def parse(self, values):
        """The strategy choosen is to convert first all possible separators (the user-defined, ',' or '\n') to one of them. And them, split the whole chain of values by that master separator. '\n' will be the master separator. """

        if values.startswith("//"):
            [custom_separator, values] = values.split("\n", 1)
            values = values.replace(custom_separator[2:], '\n')

        return values.replace(',', '\n').split('\n')
