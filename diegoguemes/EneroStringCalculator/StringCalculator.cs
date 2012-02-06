using System;
using System.Linq;

namespace EneroStringCalculator
{
    public class StringCalculator
    {
        private const string COMMA_SEPARATOR = ",";
        private const string NEW_LINE_SEPARATOR = "\n";

        public int Add(string numbers)
        {
            if(string.IsNullOrEmpty(numbers))
                return 0;
            if(!ContainsSeparator(numbers))
                return int.Parse(numbers);
            var separators = new string[] { COMMA_SEPARATOR, NEW_LINE_SEPARATOR };
            var parsedNumbers = numbers.Split(separators, StringSplitOptions.RemoveEmptyEntries);
            return parsedNumbers.Sum(n => int.Parse(n));
        }

        private bool ContainsSeparator(string numbers)
        {
            return numbers.Contains(COMMA_SEPARATOR) || numbers.Contains(NEW_LINE_SEPARATOR);
        }
    }
}