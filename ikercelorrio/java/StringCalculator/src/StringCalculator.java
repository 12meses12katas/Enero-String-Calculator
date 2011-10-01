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
        
        if (numbers.indexOf(',') == -1)
            return Integer.parseInt(numbers);
        
        String[] numArray = numbers.split(",");
        int sum, max, i;
        for ( sum = 0, i = 0, max = numArray.length ; i < max ; ++i)
        {
            sum += Integer.parseInt(numArray[i]);
        }
        return sum;
    }
}
