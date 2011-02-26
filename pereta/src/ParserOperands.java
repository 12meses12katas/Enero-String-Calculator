import java.util.ArrayList;
import java.util.List;


public class ParserOperands {
	
	private static final String DEFAULT_REGEX_DELIMITER = ",|\n";
	private static final String DEFAULT_START_CUSTOM_DELIMITER = "//[";
	private static final String DEFAULT_END_CUSTOM_DELIMITER = "]\n";
	private static final String DEFAULT_CUSTOM_DELIMITER_UNION = "\\]\\[";
	private static final String NEW_LINE = "\n";
	private static final String UNION = "|";
	
	private String stringToWork;
	
	public ParserOperands(){
		stringToWork = null;
	}
	
	public List<String> parse(String string){		
		this.stringToWork = new String(string);
		
		List<String> operandos = new ArrayList<String>();
		if(stringToWork.isEmpty())
			return operandos;
		
		String regexDelimiters = parseDelimiters();
		String[]arrayOperandos = stringToWork.split(regexDelimiters);
		
		for (String operando : arrayOperandos) {
			operandos.add(operando);
		}
		return operandos;
	}
	
	private String parseDelimiters(){
		if(stringToWork.startsWith(DEFAULT_START_CUSTOM_DELIMITER)){
			int indexBegin = DEFAULT_START_CUSTOM_DELIMITER.length();
			int indexEnd = stringToWork.indexOf(DEFAULT_END_CUSTOM_DELIMITER);
			String delimiter = stringToWork.substring(indexBegin, indexEnd);
			delimiter = delimiter.replaceAll(DEFAULT_CUSTOM_DELIMITER_UNION, UNION);
			this.stringToWork = stringToWork.substring(stringToWork.indexOf(NEW_LINE)+1);
			return delimiter;			
		}
		return DEFAULT_REGEX_DELIMITER;
	}
}
