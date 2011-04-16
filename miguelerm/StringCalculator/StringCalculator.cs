using System;
using System.Linq;
using System.Text.RegularExpressions;
using System.Collections.Generic;

namespace Mike.Katas
{
	public class StringCalculator
	{
		
		public static int Add(string numbers)
		{

			if(string.IsNullOrEmpty(numbers))
				return 0;
			
			Regex multipleSeparatorExpression = new Regex(@"^//(\[(?<separator>.+?)])+\n?(?<numbers>.*)$");
			Regex singleSeparatorExpression = new Regex(@"^//(?<separator>.+?){1}\n?(?<numbers>.*?)$");
			string separatorPattern = string.Empty;
			List<string> nums = new List<string>();
			
			Match singleSeparatorMatch = singleSeparatorExpression.Match(numbers);
			Match multipleSeparatorMatch = multipleSeparatorExpression.Match(numbers);
			
			if (multipleSeparatorMatch.Success)
			{
				
				CaptureCollection separators = multipleSeparatorMatch.Groups["separator"].Captures;
				
				for (int i = 0; i < separators.Count; i++) 
					separatorPattern += Regex.Escape(separators[i].Value) + (i < separators.Count - 1 ? "|" : null);
				
			 	numbers = multipleSeparatorMatch.Groups["numbers"].Value;
					
			}
			else if(singleSeparatorMatch.Success)
			{
				
				separatorPattern = Regex.Escape(singleSeparatorMatch.Groups["separator"].Value);
				numbers = singleSeparatorMatch.Groups["numbers"].Value;
				
			}
			else
			{
				separatorPattern = @"\n|,";
			}

			nums.AddRange(Regex.Split(numbers, separatorPattern));
			
			int[] parsednumbers = ( from n in nums select int.Parse(n) ).ToArray();
			
			var negativeNumbers = ( from n in parsednumbers where n < 0 select n.ToString() ).ToArray() ;
			
			if (negativeNumbers.Length > 0)
			{
				
				string MessageNumbers = string.Join(", ", negativeNumbers);
				string ExceptionMessage = string.Format("negatives not allowed [{0}]", MessageNumbers);
				
				throw new InvalidOperationException(ExceptionMessage);
				
			}
			
			
			return ( from n in parsednumbers where n < 1000 select n ).Sum();
			
			
		}
		
	}
}
