using System;
using System.Collections.Generic;
using System.Text;

namespace StringCalculator.Logic
{
    public class Calculator
    {
        public int Sum(int[] numbers)
        {
            List<int> negatives = new List<int>();

            int sum = 0;
            foreach (int number in numbers)
                if (number < 0)
                    negatives.Add(number);
                else
                    if (number <= 1000)
                        sum += number;

            if (negatives.Count >0)
                throw new NegativesNotAllowedException(negatives.ToArray(),"numbers");
            else
                return sum;
        }

        public int Sum(string input)
        {
            InputParser ip = new InputParser();
            return Sum(ip.Parse(input));
        }

    }
}
