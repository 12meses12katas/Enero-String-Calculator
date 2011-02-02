class StringCalculator
{
	def input
	def separatorSet = '\n,'
	def SEP_HEADER_PATTERN = '//(.+)\n'
	
	Integer add(String stringNumbers)
	{
		input = stringNumbers
		parseAndRemoveSeparatorHeader()
		if (input.contains('-'))
			parseNegativeNumbersAndThrowException();
		else
		{
			replaceSeparatorsWithSpaces()
			return add()
		}
	}
	
	private Integer add()
	{	
		Integer total = 0;
		for (String number : input.split())
			total += number.toInteger()<1000 ? number.toInteger() : 0
		return total
	}
	
	private void parseAndRemoveSeparatorHeader()
	{
		separatorSet += input.find(SEP_HEADER_PATTERN){ match, sep -> return sep};
		input = input - ~SEP_HEADER_PATTERN
	}	
	
	private void replaceSeparatorsWithSpaces()
	{
		input = input.tr(separatorSet, ' ')
	}
	
	private void parseNegativeNumbersAndThrowException()
	{
		def exceptionMessage = 'negatives not allowed: '
		def matcher = input =~ /(-\d*)/
		for(String[] match : matcher)
			exceptionMessage += ' ' + match[0]
		throw new Exception(exceptionMessage)
	}
}