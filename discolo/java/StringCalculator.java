package es.rromero.kata1;

/**
 * This class is used to Add indefinite numbers separated by any configurable number of delimiters. 
 * The first part can or cannot be a header containing the format of the delimiter(s). If it is not included
 * the default separator is used ";". Numbers can also be delimited by "\n". <br>
 * If a header is set, it must be started with // and ended by \n. The header format is described in {@link StringCalculator#calculateDelimitersRegEx(String)}
 * <p>The following restrictions are applied
 * <li>Numbers greater than 1000 will be ignored
 * <li>Negative numbers will result in a RuntimeException
 * 
 * <p><br>Some examples are given:
 * <li> //,\n2,3 --> Result: 5
 * <li> 3;3 --> Result: 6
 * <li> //[***]\n1001***2***32 --> Result: 34 (1001 is ignored)
 * @author romeror
 *
 */
public class StringCalculator {

	private static final String DEFAULT_DELIMITER = ";";
	private static final String[] SPECIAL_CHARACTERS = {"\\\\","\\.","\\*","\\+","\\?","\\-","\\&","\\^","\\$","\\<","\\>","\\[","\\]","\\|","\\{","\\}","\\(","\\)"};
	private static final String SPECIAL_CHARS_REGEX = "(\\.|\\\\|\\-|\\^|\\||\\$|\\?|\\+|\\*|\\<|\\>|\\[|\\]|\\{|\\}|\\(|\\))+";
	private static final String NEW_LINE = "\n";
	private static final String START_GROUP_DELIMITER = "[";
	private static final String END_GROUP_DELIMITER = "]";
	private static final String HEADER_START = "//";
	
	/**
	 * Calculates the Regular Expression based on the header received. 
	 * <li>It can contains a single char delimiter. e.g. ","
	 * <li>multiple char delimiter between [ ]. e.g. "[***]"
	 * <li>multiple or single char delimiters between [ ]. e.g. "[x][---]"
	 * <br><br>
	 * 
	 * @param header The string containing the delimiter(s)
	 * @return The regular expression used to tokenize the numbers
	 */
	private String calculateDelimitersRegEx(String header) {
		StringBuilder regex = new StringBuilder();
		regex.append(NEW_LINE);
		if(header == null || header.length() == 0) {
			regex.append("|").append(DEFAULT_DELIMITER);
			return regex.toString();
		}
		if(header.startsWith(START_GROUP_DELIMITER)) {
			for (int i=0; i<header.length() && i != -1; i=header.indexOf(START_GROUP_DELIMITER, i+1)) {
				String delim = header.substring(i+1, header.indexOf(END_GROUP_DELIMITER, i));
				if(delim.matches(SPECIAL_CHARS_REGEX)) {
					for(int j = 0; j<SPECIAL_CHARACTERS.length; j++) {
						delim = delim.replaceAll(SPECIAL_CHARACTERS[j], "\\" + SPECIAL_CHARACTERS[j]);
					}
				}
				regex.append("|").append(delim);
			}
		} else {
			regex.append("|").append(header);
		}
		return regex.toString();
	}
	
	/**
	 * @throws RuntimeException If a negative number is given
	 */
	public int add(String nums) {
		int result = 0;
		if(nums == null || nums.length() == 0) return 0;
		
		int startIdx = nums.indexOf(HEADER_START);
		int endIdx = nums.indexOf(NEW_LINE);
		String expression = null;
		if(startIdx != -1 && endIdx != -1) {
			expression = calculateDelimitersRegEx(nums.substring(startIdx+2, endIdx));
		} else {
			expression = calculateDelimitersRegEx(null);
		}

		String[] tokenizedNums = nums.split(expression);
		
		//Add the tokenized numbers
		for (int i = 0; i< tokenizedNums.length; i++) {
			int num = 0;
			try {
				num = Integer.parseInt(tokenizedNums[i]);
				if(num < 0) {
					throw new NegativeNumberException();
				}
				if(num > 1000) {
					num = 0;
				}
			} catch (NumberFormatException nfe) {
				// do nothing
			}
			result += num;
		}
		return result;
	}
	
	public class NegativeNumberException extends RuntimeException {

		private static final long serialVersionUID = -3033732240900592806L;
		
		public NegativeNumberException() {
			super("Negative not allowed");
		}
	}

}
