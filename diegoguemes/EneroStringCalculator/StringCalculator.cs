using System;
using System.Linq;

namespace EneroStringCalculator
{
    public class StringCalculator
    {
        private const string SEPARATOR = ",";

        public int Add(string numbers)
        {
            if(string.IsNullOrEmpty(numbers))
                return 0;
            if(!ContainsSeparator(numbers))
                return int.Parse(numbers);
            var parsedNumbers = numbers.Split(SEPARATOR.ToCharArray(), StringSplitOptions.RemoveEmptyEntries);
            return int.Parse(parsedNumbers[0]) + int.Parse(parsedNumbers[1]);
        }

        private bool ContainsSeparator(string numbers)
        {
            return numbers.Contains(SEPARATOR);
        }
    }
}