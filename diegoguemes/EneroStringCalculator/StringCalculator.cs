using System;
using System.Collections.Generic;
using System.Linq;

namespace EneroStringCalculator
{
    public class StringCalculator
    {
        private NumberSplitter splitter;

        public StringCalculator(NumberSplitter splitter)
        {
            this.splitter = splitter;
        }

        public int Add(string numbers)
        {
            if (string.IsNullOrEmpty(numbers))
                return 0;
            var parsedNumbers = splitter.SplitNumbers(numbers);
            ValidateNumbers(parsedNumbers);
            return parsedNumbers.Sum();
        }

        private void ValidateNumbers(IEnumerable<int> numbers)
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