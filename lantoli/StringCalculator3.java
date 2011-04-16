package kata;

public class StringCalculator3 {

	public int add(String str) {
		if (str.length() == 0) {
			return 0;
		}
		int commaPos = str.indexOf(',');
		if (commaPos == -1) {
			return Integer.parseInt(str);
		}
		String strNum1 = str.substring(0, commaPos);
		String strNum2 = str.substring(commaPos + 1);
		return Integer.parseInt(strNum1) + Integer.parseInt(strNum2);
	}

}
