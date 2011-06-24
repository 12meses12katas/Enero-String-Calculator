import re

class StringCalculator(object):
    def __init__(self, delimiters = [",", "\n"]):
        self.stringToParse = ""
        self.initialDelimiters = delimiters

    def add(self, theString):
        return reduce(lambda x, y: x + y, 
                      self.retrieveNumbersFromString(theString), 0)

    def retrieveNumbersFromString(self, stringWithNumbers):
        self.stringToParse = stringWithNumbers
        return self.convert2Numbers(self.getStringNumberList(
                                       self.getDelimiters()))

    def getStringNumberList(self, delimiters):
        chopped = [self.stringToParse]
        for delim in delimiters:
            chopped = self.chopWith(delim, chopped)
	return chopped

    def getDelimiters(self):
        delimiters = self.getExtraDelimiters()
        delimiters.extend(self.initialDelimiters)
        return delimiters

    def getExtraDelimiters(self):
        new_delimiters = []
        if (self.newDelimitersDefined()):
            subStringToParse = self.retrieveDelimitersStringDefinition()
            if (self.isEnclosedByBrackets(subStringToParse)):
                new_delimiters = re.split("\]\[", self.removeFirstAndLastChar\
                                                           (subStringToParse))
            else:
                new_delimiters = [subStringToParse]
        return new_delimiters

    def newDelimitersDefined(self):
        return (self.stringToParse.find("//") != -1)

    def retrieveDelimitersStringDefinition(self):
        topIndex = self.stringToParse.find("\n")
        delimitersDef = self.stringToParse[:topIndex]
        delimitersDef = delimitersDef[2:]
        self.stringToParse = self.stringToParse[topIndex + 1:]
        return delimitersDef

    def isEnclosedByBrackets(self, substr):
        return ((substr.find("[") != -1) and (substr.find("]") != -1))

    def removeFirstAndLastChar(self, substr):
        substr = substr[1:]
        substr = substr[:-1] 
        return substr

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
