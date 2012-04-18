using System;
using System.Collections.Generic;
using System.Text;

namespace StringCalculator.Logic
{
    public class NegativesNotAllowedException :ArgumentOutOfRangeException
    {
        int[] negatives;
        public NegativesNotAllowedException(int[] negatives, string paramName) :base(paramName,negatives,"Negatives not allowed")
        {
            this.negatives = negatives;
        }

        public string Negatives
        {
            get
            {
                return getString(negatives);
            }
        }

        string getString(int[] numbers)
        {
            StringBuilder stb = new StringBuilder();
            foreach (int number in numbers)
            {
                if (stb.Length > 0)
                    stb.Append(",");
                stb.Append(number);
            }
            return stb.ToString();
        }

    }
}
