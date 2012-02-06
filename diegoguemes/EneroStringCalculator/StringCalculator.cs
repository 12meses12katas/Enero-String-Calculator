using System;
using System.Collections.Generic;
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
            IEnumerable<string> separators;
            string numbersPart;
            if(numbers.StartsWith("//"))
            {
                string customSeparator = numbers.Substring(2, numbers.IndexOf("\n") - 2);
                separators = new string[] { COMMA_SEPARATOR, NEW_LINE_SEPARATOR, customSeparator };
                numbersPart = numbers.Substring(numbers.IndexOf("\n") + 1);
            }
            else
            {
                separators = new string[] { COMMA_SEPARATOR, NEW_LINE_SEPARATOR };
                numbersPart = numbers;
            }
            var parsedNumbers = numbersPart.Split(separators.ToArray(), StringSplitOptions.RemoveEmptyEntries);
            return parsedNumbers.Sum(n => int.Parse(n));
        }

        private bool ContainsSeparator(string numbers)
        {
            return numbers.Contains(COMMA_SEPARATOR) || numbers.Contains(NEW_LINE_SEPARATOR);
        }
    }
}