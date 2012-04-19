using System.Collections.Generic;
using System.Linq;

namespace EneroStringCalculator
{
    public class GreaterThanFilter
    {
        private int maxLimit;

        public GreaterThanFilter(int maxLimit)
        {
            this.maxLimit = maxLimit;
        }

        public IEnumerable<int> Filter(IEnumerable<int> numbers)
        {
            return numbers.Where(n => n <= maxLimit);
        }
    }
}