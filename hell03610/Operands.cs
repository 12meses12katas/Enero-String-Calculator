using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StringCalculator
{
    class Operands
    {
        private string rawOperands;
        private const string CUSTOM_DELIMITER_START = "//";
        private const string CUSTOM_DELIMITER_END = "\n";

        public Operands(string operands)
        {
            this.rawOperands = operands;
        }

        public List<int> getOperands()
        {
            List<int> operands = new List<int>();

            string [] delimiters = getDelimiters();
            string cleandOperands = getCleanOperands();

            foreach (string operand in cleandOperands.Split(delimiters, StringSplitOptions.RemoveEmptyEntries))
            {
                operands.Add(Int32.Parse(operand));
            }

            return operands;
        }

        private string getCleanOperands()
        {
            string cleanedOperands = rawOperands;

            if (hasCustomDelimiter())
            {
                int endCustomDelimiter = cleanedOperands.IndexOf(CUSTOM_DELIMITER_END) + CUSTOM_DELIMITER_END.Length - 1;
                cleanedOperands = cleanedOperands.Remove(0, endCustomDelimiter);
            }

            return cleanedOperands;

        }

        private string[] getDelimiters()
        {
            List<string> delimiters = new List<string> { ",", "\n" };

            if (hasCustomDelimiter())
            {
                delimiters.AddRange(getCustomDelimiter());
            }

            return delimiters.ToArray();
        }

        private string [] getCustomDelimiter()
        {
            int endIndexOfDelimiter = rawOperands.IndexOf(CUSTOM_DELIMITER_END);
            int startCustomDelimiter = CUSTOM_DELIMITER_START.Length;
            int endCustomDelimiter = endIndexOfDelimiter - startCustomDelimiter;

            string customDelimiters = rawOperands.Substring(startCustomDelimiter, endCustomDelimiter);
            
            return customDelimiters.Split(new string[] {"[","]"}, StringSplitOptions.RemoveEmptyEntries); 
        }

        private bool hasCustomDelimiter()
        {
            return rawOperands.StartsWith(CUSTOM_DELIMITER_START);
        }

    }
}
