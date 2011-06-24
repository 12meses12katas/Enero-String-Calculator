package kata;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringCalculator9 {

	public int add(String str) {
		String delimiter = ",\n";
		if (str.startsWith("//[")) {
			delimiter += str.substring(3, 4);
			str = str.substring(str.indexOf('\n'));
		}
		List<Integer> negatives = new ArrayList<Integer>();
		int sum = 0;
		StringTokenizer token = new StringTokenizer(str, delimiter);
		while (token.hasMoreElements()) {
			int num = Integer.parseInt(token.nextToken());
			if (num < 0) {
				negatives.add(num);
			}
			if (num <= 1000) {
				sum += num;
			}
		}
		if (negatives.size() > 0) {
			throw new IllegalArgumentException("no se permiten negativos: "
					+ negatives.toString());
		}
		return sum;
	}
}
