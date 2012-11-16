import java.util.LinkedList;
import java.util.List;


public class Calculator {

	private static final int MAX_VALID_ADDEND = 1000;
	private static final int MIN_VALID_ADDEND = 0;

	private Delimiter delimiterParser;
	
	public Calculator(){
		delimiterParser = new Delimiter();
	}
	
	public int add(String addendsWithDelimiters) {

		addendsWithDelimiters = preventEmptyAddens(addendsWithDelimiters);

		String delimiter = delimiterParser.getDelimiter(addendsWithDelimiters);
		String[] addends = getAddends(addendsWithDelimiters, delimiter);

		return add(addends);
	}

	private int add(String[] addendSet) {
		int result = 0;
		
		this.checkNegativeAddens(addendSet);

		for (String stringAddend : addendSet) {
			
			int addend = Integer.valueOf(stringAddend); 
			boolean isMaxValidAddend = addend <= MAX_VALID_ADDEND; 

			if(isMaxValidAddend)
			{
				result += addend;
			}
		}
		return result;
	}
	
	private void checkNegativeAddens(String []addendSet){
		
		List<String> invalidAddends = new LinkedList<String>();
		 
		
		for (String stringAddend : addendSet) {

			int addend = Integer.valueOf(stringAddend); 
			boolean isMinValidAddend = addend >= MIN_VALID_ADDEND; 
			
			if(!isMinValidAddend)
			{
				invalidAddends.add(stringAddend);
			}
		}

		if(!invalidAddends.isEmpty()){
			throw new NegativesNotAllowedException(invalidAddends);			
		}
	}
	

	private String preventEmptyAddens(String addendSet) {

		boolean emptyAddends = ("".equals(addendSet));
		if (emptyAddends) {
			addendSet = "0";
		}

		return addendSet;
	}

	private String[] getAddends(String addendSet, String delimiter) {

		boolean emptyAddends = ("".equals(addendSet));
		if (emptyAddends) {
			return new String[] { "0" };
		}
	
		addendSet = delimiterParser.removeDelimiter(addendSet);
		
		return addendSet.split(delimiter);
	}

}
