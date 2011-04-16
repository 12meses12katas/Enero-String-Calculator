import java.util.ArrayList;
import java.util.List;


public class StringCalculator {

	private ParserOperands parserOperandos;
	private static final int MAX = 1000;
	
	public StringCalculator(){
		parserOperandos = new ParserOperands();
	}
	
	public int add(String string) {
		int result = 0;
		List<String> operandos = parserOperandos.parse(string);
		
		checkNegatives(operandos);
		
		for (String operando : operandos) {
			if(Integer.parseInt(operando) <= MAX)
				result += Integer.parseInt(operando);
		}
		
		return result;
	}

	private void checkNegatives(List<String> operandos) {
		List<String> negatives = new ArrayList<String>();
		for (String operando : operandos) {
			if(Integer.parseInt(operando) < 0)
				negatives.add(operando);
		}
		if(negatives.size() > 0){
			throw new NegativesNotAllowedException("Negatives not allowed " + negatives.toString());
		}
	}
}
