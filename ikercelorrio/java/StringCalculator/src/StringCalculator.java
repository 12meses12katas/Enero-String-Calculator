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
        return Integer.parseInt(numbers);
    }
}
