import java.util.List;


public class NegativesNotAllowedException extends RuntimeException {

	private List<String> invalidAddens; 
	
	public NegativesNotAllowedException(List<String> invalidAddends) {
		this.invalidAddens = invalidAddends;
	}
	
	@Override
	public String getMessage(){
		
		StringBuffer numberMessage = new StringBuffer();
		for (String number : invalidAddens) {
			numberMessage.append(number);
			numberMessage.append(", ");
		}
		
		return "The following addens are invalid: [" + numberMessage.toString() + "]";
	}
}
