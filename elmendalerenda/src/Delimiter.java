
public class Delimiter {

	private static final String REGEX_UNION = "|";
	private static final String DEFAULT_DELIMITER_REGEX = ",|\n";
	private static final String CUSTOM_DELIMITER_END = "]\n";
	private static final String CUSTOM_DELIMITER_STARTER = "//[";
	private static final String CUSTOM_DELIMITER_UNION = "\\]\\[";

	
	public String removeDelimiter(String addendSet) {
		int delimiterOffset = 0;
		
		if (hasCustomDelimiter(addendSet)) 
		{
			int delimiterEnd = addendSet.indexOf(CUSTOM_DELIMITER_END);

			delimiterOffset = delimiterEnd + CUSTOM_DELIMITER_END.length();
		}
		return addendSet.substring(delimiterOffset);
	}

	public String getDelimiter(String addendSet) {

		String delimiter = DEFAULT_DELIMITER_REGEX;

		if (hasCustomDelimiter(addendSet)) 
		{
			int delimiterEnd = addendSet.indexOf(CUSTOM_DELIMITER_END);

			delimiter += REGEX_UNION + addendSet.substring(CUSTOM_DELIMITER_STARTER.length(), delimiterEnd);
			
			delimiter = delimiter.replaceAll(CUSTOM_DELIMITER_UNION, REGEX_UNION);
		}
		
		return delimiter;
	}
	

	private boolean hasCustomDelimiter(String addendSet) {
		return addendSet.startsWith(CUSTOM_DELIMITER_STARTER);
	}
	
	
}
