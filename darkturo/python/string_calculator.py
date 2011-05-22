import re

class StringCalculator(object):
    def __init__(self, delimiters = [","]):
        self.stringToParse = ""
        self.delimiters = delimiters
        self.numbers = []

    def add(self, theString):
        self.parseNumbersFromString(theString)
        return reduce(lambda x, y: x + y, self.numbers, 0)

    def parseNumbersFromString(self, stringWithNumbers):
        self.numbers = []
        self.stringToParse = stringWithNumbers

        delimiters = self.getExtraDelimiters()
        delimiters.extend(self.delimiters)

        chopped = [self.stringToParse]
        for delim in delimiters:
            chopped = self.chopWith(delim, chopped)

        self.numbers = self.convert2Numbers(chopped)

    def getExtraDelimiters(self):
        return []

    def chopWith(self, delim, stringList):
        newList = []
        for chunk in stringList:
            newList.extend(self._chopWith(delim, chunk))
        return newList

    def _chopWith(self, delim, chunk):
        if (len(chunk) > 0):
            return re.split(delim, chunk)
        else:
            return []

    def convert2Numbers(self, theList):
        return map(lambda x: int(x), theList)
