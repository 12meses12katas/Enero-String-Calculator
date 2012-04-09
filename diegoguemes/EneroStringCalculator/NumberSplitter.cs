using System;
using System.Collections.Generic;
using System.Linq;

namespace EneroStringCalculator
{
    public class NumberSplitter
    {
        private const string COMMA_SEPARATOR = ",";
        private const string NEW_LINE_SEPARATOR = "\n";
        private const string SEPARATOR_PART_START = "//";
        private const string SEPARATOR_PART_END = "\n";
        private const string COMPLEX_CUSTOM_SEPARATOR_START = "[";
        private const string COMPLEX_CUSTOM_SEPARATOR_END = "]";

        public IEnumerable<int> SplitNumbers(string numbers)
        {
            var numbersPart = GetNumbersPart(numbers);
            var separatorsPart = GetSeparatorsPart(numbers);
            var separators = GetSeparators(separatorsPart);
            return numbersPart.Split(separators, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse);
        }

        private string GetNumbersPart(string numbers)
        {
            if (!numbers.StartsWith(SEPARATOR_PART_START))
                return numbers;
            return numbers.Substring(numbers.IndexOf(SEPARATOR_PART_END) + 1);
        }

        private string GetSeparatorsPart(string numbers)
        {
            if (!numbers.StartsWith(SEPARATOR_PART_START))
                return string.Empty;
            return numbers.Substring(SEPARATOR_PART_START.Length,
                                     numbers.IndexOf(SEPARATOR_PART_END) - SEPARATOR_PART_START.Length);
        }

        private string[] GetSeparators(string separatorsPart)
        {
            var defaultSeparators = new[] {COMMA_SEPARATOR, NEW_LINE_SEPARATOR};
            return defaultSeparators.Union(GetCustomSeparators(separatorsPart)).ToArray();
        }

        private IEnumerable<string> GetCustomSeparators(string separatorsPart)
        {
            return separatorsPart.Split(new[] {COMPLEX_CUSTOM_SEPARATOR_START, COMPLEX_CUSTOM_SEPARATOR_END},
                                        StringSplitOptions.RemoveEmptyEntries);
        }


    }
}