using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StringCalculator
{
	public class AddendsParser
	{
		private string header;
		private string body;

		public AddendsParser(string calculatorInput)
		{
			ExtractHeaderAndBodyFrom(calculatorInput);
		}

		private void ExtractHeaderAndBodyFrom(string calculatorInput)
		{
			if (CalculatorInputHasHeader(calculatorInput))
			{
				header = calculatorInput.Split('\n')[0];
				body = calculatorInput.Split('\n')[1];
			}
			else
			{
				header = String.Empty;
				body = calculatorInput;
			}
		}

		private bool CalculatorInputHasHeader(string calculatorInput)
		{
			return calculatorInput.StartsWith("//");
		}

		public Addends GetAddends()
		{
			string[] delimitators = GetDelimitators();
            string[] addendsAsStrings = body.Split(delimitators, ApplySplitOption());

            var addendsAsIntegers = new Addends();
            addendsAsIntegers.AddRangeFrom(addendsAsStrings);
            addendsAsIntegers.CheckAddensNotNegative();

            return addendsAsIntegers;
		}

        private StringSplitOptions ApplySplitOption() 
        {
            if (header != String.Empty)
            {
                return StringSplitOptions.RemoveEmptyEntries;
            }

            return StringSplitOptions.None;
        }

		private String[] GetDelimitators() 
		{
			var delimitators = new List<string>();

            if (header != String.Empty)
            {
                foreach (var item in header.Split('['))
                {
                    if (item.Contains(']'))
                    {
                        delimitators.Add(item.Split(']')[0]);
                    }
                }
            }
            else
            {
                delimitators.Add(",");
                delimitators.Add("\n");
            }

			return delimitators.ToArray<string>();
		}	
	}
}