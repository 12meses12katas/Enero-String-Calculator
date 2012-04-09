using System.Linq;

namespace EneroStringCalculator
{
    public class StringCalculator
    {
        private NumberSplitter splitter;
        private NegativesValidator negativesValidator;
        private GreaterThanFilter filter;

        public StringCalculator(NumberSplitter splitter, NegativesValidator negativesValidator, GreaterThanFilter filter)
        {
            this.splitter = splitter;
            this.negativesValidator = negativesValidator;
            this.filter = filter;
        }

        public int Add(string numbers)
        {
            var parsedNumbers = splitter.SplitNumbers(numbers);
            negativesValidator.ValidateNumbers(parsedNumbers);
            parsedNumbers = filter.Filter(parsedNumbers);
            return parsedNumbers.Sum();
        }
    }
}