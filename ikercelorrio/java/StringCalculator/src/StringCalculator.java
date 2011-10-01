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
        
        String[] numArray = numbers.split(",");
        int sum = 0;       
        for ( String num : numArray)
        {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}
