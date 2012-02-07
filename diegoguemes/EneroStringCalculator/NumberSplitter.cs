using System;
using System.Collections.Generic;
using System.Linq;

namespace EneroStringCalculator
{
    public class NumberSplitter
    {
        private const string COMMA_SEPARATOR = ",";
        private const string NEW_LINE_SEPARATOR = "\n";
        private const string CUSTOM_SEPARATOR_START = "//";
        private const string CUSTOM_SEPARATOR_END = "\n";

        public IEnumerable<string> SplitNumbers(string numbers)
        {
            string numbersPart = GetNumbersPart(numbers);
            string[] separators = GetSeparators(numbers);
            var parsedNumbers = numbersPart.Split(separators, StringSplitOptions.RemoveEmptyEntries);
            if(parsedNumbers.Any(n => int.Parse(n) < 0))
            {
                throw new ArgumentException(string.Format("Negatives not allowed: {0}",
                                                          string.Join(",", parsedNumbers.Where(n => int.Parse(n) < 0))));
            }
            return parsedNumbers;
        }

        private string GetNumbersPart(string numbers)
        {
            if (!numbers.StartsWith(CUSTOM_SEPARATOR_START))
                return numbers;
            return numbers.Substring(numbers.IndexOf(CUSTOM_SEPARATOR_END) + 1);
        }

        private string[] GetSeparators(string numbers)
        {
            if (!numbers.StartsWith(CUSTOM_SEPARATOR_START))
                return new[] { COMMA_SEPARATOR, NEW_LINE_SEPARATOR };
            return new[] { COMMA_SEPARATOR, NEW_LINE_SEPARATOR, GetCustomSeparator(numbers) };
        }

        private string GetCustomSeparator(string numbers)
        {
            return numbers.Substring(CUSTOM_SEPARATOR_START.Length,
                                     numbers.IndexOf(CUSTOM_SEPARATOR_END) - CUSTOM_SEPARATOR_START.Length);
        }
    }
}