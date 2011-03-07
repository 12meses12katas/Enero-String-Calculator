using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StringCalculator
{
    public class StringCalculator
    {
        public StringCalculator(){}

        public int Add(string calculatorInput) 
        {
            var parser = new AddendsParser(calculatorInput);
            var addends = parser.GetAddends();
            return addends.Sum();
        }
    }
}