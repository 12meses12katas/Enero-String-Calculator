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
            return parsedNumbers.Sum(n => int.Parse(n));
        }


    }
}