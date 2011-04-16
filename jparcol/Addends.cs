using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StringCalculator
{
    public class Addends
    {
        List<int> allAddends = new List<int>();

        public Addends() 
        {
            allAddends = new List<int>();
        }

        public void AddRangeFrom(string[] addends)
        {
            if (addends.Length > 1)
            {
                for (int i = 0; i < addends.Length; i++)
                {
                    if (String.IsNullOrWhiteSpace(addends[i]))
                    {
                        throw new Exception("formato invalido");
                    }
                }
            }

            for (int i = 0; i < addends.Length; i++)
            {
                int result = 0;
                try
                {
                    if (String.IsNullOrEmpty(addends[i]))
                    {
                        addends[i] = "0";
                    }
                    result = Int16.Parse(addends[i]);
                }
                catch (Exception e)
                {
                    throw new Exception(e.Message);
                }

                allAddends.Add(result);
            }
        }

        public List<int> GetOnlyNegatives()
        {
            return allAddends.Where(op => op < 0).ToList();
        }

        public List<int> GetOnlyPositives()
        {
            return allAddends.Where(op => op > 0).ToList();
        }

        public List<int> GetPositivesSmallerThan1000()
        {
            return allAddends.Where(op => op > 0 && op <= 1000).ToList();
        }

        public bool IsThereAnyAddendNegative()
        {
            return GetOnlyNegatives().Count > 0;
        }

        public int Sum()
        {
            return GetPositivesSmallerThan1000().Sum();
        }

        public void CheckAddensNotNegative()
        {
            if (IsThereAnyAddendNegative())
            {
                throw new Exception("Operadores negativos no soportados");
            }
        }
    }
}
