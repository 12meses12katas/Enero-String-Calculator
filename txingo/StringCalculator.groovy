

class StringCalculator {
    
    def add(String numbers){
        if(! numbers) return 0
        
        def delimiter = computeDelimiter(numbers)
        def withoutDefiningDelimiter = removeDefiningDelimiter(numbers, delimiter.count)
        def withoutNewLine = withoutDefiningDelimiter.replace("\n", delimiter)
        def numbersArray = withoutNewLine.split(delimiter)
        
        def sum = 0
        def negatives = []
        numbersArray.each { String number ->
            def n = number.toInteger()
            if(n < 0) negatives << n
            if(n <= 1000) sum += n
        }
        
        if(negatives) throw new NegativesException(negatives:negatives)
        
        return sum
    }
    
    private String computeDelimiter(String numbers){
        if(numbers.startsWith("//")){
            def endIndex = numbers.indexOf("\n")
            def enclosedDelimiter = numbers.substring(2, endIndex)
            return enclosedDelimiter.substring(1, enclosedDelimiter.count - 1)
        }
        return ","
    }
    
    private String removeDefiningDelimiter(String numbers, int length) {
        def emptyDelimiter = "//[]\n"
        if(numbers.startsWith("//"))
            return numbers.substring(length + emptyDelimiter.count) 
        return numbers
    }
}
