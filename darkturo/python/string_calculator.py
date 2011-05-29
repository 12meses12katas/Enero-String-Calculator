import re

class StringCalculator(object):
    def __init__(self, delimiters = [",", "\n"]):
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
        new_delimiters = []
        if (self.stringToParse.find("//") != -1):
            topIndex = self.stringToParse.find("\n")
            subStringToParse = self.stringToParse[:topIndex]
            self.stringToParse = self.stringToParse[topIndex + 1:]
            if ((subStringToParse.find("[") != -1) and (subStringToParse.find("]"))):
                subStringToParse = subStringToParse[3:]
		subStringToParse = subStringToParse[:-1] 
                new_delimiters = re.split("\]\[", subStringToParse)
            else:
                new_delimiters = [subStringToParse[2:]]
        return new_delimiters

    def chopWith(self, delim, stringList):
        newList = []
        for chunk in stringList:
            newList.extend(self._chopWith(delim, chunk))
        return newList

    def _chopWith(self, delim, chunk):
        chopped_with_delim = []        
        if (len(chunk) > 0):
            index = 0
            while(chunk.find(delim) != -1):
                index = chunk.find(delim)
                chopped_with_delim.append(chunk[:index])
                chunk = chunk[index + len(delim):]
            chopped_with_delim.append(chunk)
        return chopped_with_delim

    def convert2Numbers(self, theList):
        numbers = []
        negatives = []
        for strNumber in (theList):
            num = int(strNumber)
            if (num < 0):
                negatives.append(num)
            elif (num < 1000): 
                numbers.append(num)

	if (len(negatives) > 0):
            msg = "negatives not allowed: %s" % ",".join(map(lambda x: "%s"%x,
                                                             negatives))
            raise Exception(msg)

        return numbers
