import java.util.ArrayList;
import java.util.List;




public class StringCalculator {
	static final String DEFAULT_DELIMITERS = ",|\n";
	
	private boolean hasDelimiterDefinition(String parameters){
		if (parameters == null) {
			return false;
		}
		if (parameters.length() >= 4 && parameters.charAt(0) == '/') {
			return true;
		}
		return false;
	}
	
	private String extractDelimiters(String parameters) {
		if (hasDelimiterDefinition(parameters)) {
			return new String(parameters.substring(2,3));
		}
		return DEFAULT_DELIMITERS;
	}
	
	private String removeDelimitersDefinition(String parameters){
		if (hasDelimiterDefinition(parameters))
		{
			return new String(parameters.substring(4));
		}
		return parameters;
	}
	
	public Integer addStrings(String parameters) throws NegativesNotAllowed {
		Integer result = 0;
		
		String delimiters = extractDelimiters(parameters);
		parameters = removeDelimitersDefinition(parameters);
		
		if (parameters != "" && parameters != null) {
			String stringParameters[];
			
			stringParameters = parameters.split(delimiters);
			
			String negatives = "";
			for ( int i = 0 ; i < stringParameters.length ; i++ ) {
				Integer value = Integer.parseInt(stringParameters[i]);
			
				if (value < 0) {
					negatives += " " + value;
				}
				result += Integer.parseInt(stringParameters[i]);
			}
			
			if (negatives != "") {
				// Hay negativos
				throw new NegativesNotAllowed("Values" + negatives);
			}
			
			
		}
		return result;
	}
}
