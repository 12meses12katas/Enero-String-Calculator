using System;
using System.Collections.Generic;
using System.Linq;

namespace EneroStringCalculator
{
    public class NegativesValidator
    {
        public void ValidateNumbers(IEnumerable<int> numbers)
        {
            var negatives = GetNegatives(numbers);
            if (negatives.Count() > 0)
            {
                throw new ArgumentException(string.Format("Negatives not allowed: {0}",
                                                          string.Join(",", negatives)));
            }
        }

        private IEnumerable<int> GetNegatives(IEnumerable<int> numbers)
        {
            return numbers.Where(IsNegative);
        }

        private bool IsNegative(int number)
        {
            return number < 0;
        }    
    }
}