
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
            numbers = numbers.substring(numbers.indexOf('\n')+1);
        }
        return numbers.split(delimiters);
    }
    
    private int AddNumbers(String[] numbers)
    {
        int sum = 0;       
        for ( String num : numbers )
        {
            sum += toInt(num);
        }
        return sum;    
    }
    
    private int toInt(String number)
    {
        if ( number.isEmpty() ) 
            return 0;
        return Integer.parseInt(number);
    }
}
