
import java.util.regex.Pattern;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iker
 */
public class StringCalculator {
    public int Add(String numbers)
    {
        if (numbers.isEmpty()) 
            return 0;
        
        String[] numArray = GetNumbers(numbers);
        
        return AddNumbers(numArray);
    }
    
    private String[] GetNumbers(String numbers)
    {
        String delimiters = "[,\n]";
        if ( numbers.startsWith("//"))
        {
            delimiters = Pattern.quote(numbers.substring(2,3));
            numbers = numbers.substring(numbers.indexOf('\n',3)+1);
        }
        return numbers.split(delimiters);
    }
    
    private int AddNumbers(String[] numbers)
            throws NumberFormatException
    {
        int sum = 0;
        String negativeNumbers = new String();
        
        for ( String num : numbers )
        {
            if ( num.charAt(0) == '-' ) 
                negativeNumbers += num + ", ";
            else
                sum += toInt(num);
        }
        if ( !negativeNumbers.isEmpty() )
        {
            negativeNumbers = negativeNumbers.substring(0, negativeNumbers.length()-1);
            throw new NumberFormatException("negatives not allowed : " + negativeNumbers);
        }
        return sum;    
    }
    
    private int toInt(String number)
    {
        if ( number.isEmpty() ) 
            return 0;
        int n = Integer.parseInt(number);
        return n;
    }
    
}
