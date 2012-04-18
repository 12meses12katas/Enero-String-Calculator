using System;
using System.Collections.Generic;
using System.Text;
using StringCalculator.Logic;

namespace StringCalculator.Test
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] tests = {"","1","1,2","1,2,3","1,2,3,4","1\n2,3","//;\n1;2","-1,2,-5", "1001,2","//[***]\n1***2***3","//[*][%]\n1*2%3", "//[^^][*]\n1^^2*3"};

            foreach (string test in tests)
            {
                Console.WriteLine(String.Format("Test: {0}", test));
                try
                {
                    Console.WriteLine(string.Format("Result: {0}", new Calculator().Sum(test)));
                }
                catch (NegativesNotAllowedException ex)
                {
                    Console.WriteLine("Controlled Exception:");
                    Console.WriteLine(ex.Message);
                    Console.WriteLine(ex.Negatives);
                }
                Console.WriteLine("-------------");
            }
            Console.WriteLine("Test Ended. Press Enter to continue.");
            Console.Read();
        }
    }
}
