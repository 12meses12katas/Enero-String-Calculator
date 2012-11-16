package stringCalculator;

import java.util.ArrayList;


public class StringCalculator {

	private static final String VOID = "";
	private static final String END_SEPARATOR_WHEN_MORE_CHARS = "]";
	private static final String INI_SEPARATOR_WHEN_MORE_CHARS = "[";
	private static final int NEGATIVE_LIMIT = 0;
	private static final String NEW_LINE = "\n";
	private static final String INI_CUSTOM_SEPARATOR = "//";
	private static final String DEFAUL_SEPARATOR = "\\,|\n";
	private static final int MAX_NUMBER_TO_ADD = 1000;

	private String separator = DEFAUL_SEPARATOR;
	ArrayList<Integer> negativeValues;
	int totalSum = 0;


	public int Add(String numbers) throws NegativeException{
		
		if(!numbers.equals(VOID)){
			if(numbers.startsWith(INI_CUSTOM_SEPARATOR)){
				separator = GetSeparator(numbers);
				numbers = getNewNumbers(numbers);			
			}
			String[] numbersArray = numbers.split(separator);			
			totalSum = AddNumbers(numbersArray);
			CheckNegativeException(negativeValues);
		}	
		return totalSum;
	}


	private String getNewNumbers(String numbers) {
		numbers = numbers.split(NEW_LINE)[1];
		return numbers;
	}


	private String GetSeparator(String numbers) {
		String separator;
		separator = numbers.split(NEW_LINE)[0].replace(INI_CUSTOM_SEPARATOR, VOID);
		if(separator.startsWith(INI_SEPARATOR_WHEN_MORE_CHARS)&&separator.endsWith(END_SEPARATOR_WHEN_MORE_CHARS)){
			separator =separator.replace(INI_SEPARATOR_WHEN_MORE_CHARS, VOID).replace(END_SEPARATOR_WHEN_MORE_CHARS, VOID);
		}
		return separator;
	}


	private int AddNumbers(String[] numbersArray) {
		negativeValues = new ArrayList<Integer>();
		for(int i=0;i<numbersArray.length;i++){
			int numberToAdd = Integer.valueOf(numbersArray[i]);
			numberToAdd = ReturnValidNumbers(negativeValues, numberToAdd);
			totalSum = totalSum + numberToAdd;
		}
		return totalSum;
	}


	private void CheckNegativeException(ArrayList<Integer> negativeValues)
	throws NegativeException {
		if(negativeValues.size()>0){
			throw new NegativeException(negativeValues);
		}
	}


	private int ReturnValidNumbers(ArrayList<Integer> negativeValues,
			int numberToAdd) {
		if(numberToAdd<NEGATIVE_LIMIT){
			negativeValues.add(numberToAdd);
		}else if(numberToAdd>MAX_NUMBER_TO_ADD){
			numberToAdd = 0;
		}
		return numberToAdd;
	}


	public class NegativeException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NegativeException(ArrayList<Integer> negativeNumbers){		
			System.out.print("Negative Numbers not allowed - " + negativeNumbers.toString());
		}

	}

}
