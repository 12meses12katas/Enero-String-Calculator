using System;
using System.Collections.Generic;
using System.Text;

namespace StringCalculator.Logic
{
    internal class InputParser
    {
        public int[] Parse(string input)
        {
            List<string> delimiters = new List<string>();
            delimiters.AddRange(new string[] {",","\n"});
            if (hasSpecialDelimiter(input))
            {
                delimiters.AddRange(getSpecialDelimiters(input));
                input = removeSpecialDelimiterLine(input);
            }

            List<int> numbers = new List<int>();
            if (!String.IsNullOrEmpty(input.Trim()))
                foreach (string part in input.Split(delimiters.ToArray(), StringSplitOptions.RemoveEmptyEntries))
                    numbers.Add(int.Parse(part));

            return numbers.ToArray();
        }

        bool hasSpecialDelimiter(string input)
        {
            return (input.StartsWith("//"));
        }

        string[] getSpecialDelimiters(string input)
        {
            List<string> delimiters = new List<string>();
            string delimiterLine = input.Substring(2, input.IndexOf('\n') -2);
            StringBuilder stbDelimiter = new StringBuilder();
            bool inDelimiter = false;
            foreach (char c in delimiterLine)
            {
                switch (c)
                {
                    case '[':
                        inDelimiter = true;
                        stbDelimiter = new StringBuilder();
                        break;
                    case ']':
                        inDelimiter = false;
                        delimiters.Add(stbDelimiter.ToString());
                        break;
                    default:
                        if (inDelimiter)
                            stbDelimiter.Append(c);
                        else
                            delimiters.Add(c.ToString());
                        break;
                }
            }
            return delimiters.ToArray();
        }

        string removeSpecialDelimiterLine(string input)
        {
            int startIndex = input.IndexOf('\n') + 1;
            return input.Substring(startIndex);
        }
    }

}
