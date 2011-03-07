using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StringCalculator
{
    public class Calculator
    {
        private const int MINIMUM_ADDEND_ALLOWED = 0;
        private const int MAXIMUM_ADDEND_ALLOWED = 1000;

        public int Add(string addition)
        {
            Operands operands = new Operands(addition);
            List<int> addends =  operands.getOperands();

            throwExceptionAgainstNotAllowedAddends(addends);
            addends = discardNotAllowedAddends(addends);

            return add(addends);
        }

        private static void throwExceptionAgainstNotAllowedAddends(List<int> addends)
        {
            IEnumerable<int> negativeAddens = addends.Where(addend => (addend < MINIMUM_ADDEND_ALLOWED));
            if (negativeAddens.Count<int>() > 0)
            {
                throw new ArgumentException(string.Format("Negavite numbers not allowed. Negative numbers found: {0}", string.Join(",", negativeAddens)));
            }
        }

        private static List<int> discardNotAllowedAddends(List<int> addends)
        {
            return addends.Where(addend => (addend <= MAXIMUM_ADDEND_ALLOWED)).ToList<int>();
        }

        private static int add(List<int> addends)
        {
            return addends.Sum();
        }
     
    }
}
