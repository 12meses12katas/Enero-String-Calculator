
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
            numbers = numbers.substring(4);
        }
        return numbers.split(delimiters);
    }
     
    private String CheckNegativeNumber(String negativeNumbers, String number)
    {
        if ( number.charAt(0) == '-' ) 
        {
            if ( negativeNumbers.isEmpty() )
                negativeNumbers = "negatives not allowed : " + number;
            else
                negativeNumbers += ", " + number;
        }
        return negativeNumbers;            
    }
    
    private int AddNumbers(String[] numbers)
            throws NumberFormatException
    {
        int sum = 0;
        String negativeNumbers = new String();
        
        for ( String num : numbers )
        {
            negativeNumbers = CheckNegativeNumber(negativeNumbers, num);
            if ( negativeNumbers.isEmpty() ) 
                sum += toInt(num);
        }
        if ( !negativeNumbers.isEmpty() )
            throw new NumberFormatException(negativeNumbers);
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
