using System;
using System.Collections.Generic;

namespace StringCalculator
{
    public class Calculator
    {
        private const char LIMITADOR_ALTERNATIVO = '\n';
        private const char LIMITADOR = ',';
        private const string ARGUMENT = "\\"; 

        public static int Add(string cadena)
        {
            int result = 0;
            if (cadena != String.Empty)
            {
                List<char> limitador = new List<char> {LIMITADOR_ALTERNATIVO, LIMITADOR};

                string[] matrix = GetCadenas(cadena, limitador);

                result = GetResult(matrix, result);
            }
            return result;
        }

        private static int GetResult(string[] matrix, int result)
        {
            foreach (var valor in matrix)
            {
                if (!String.IsNullOrEmpty(valor))
                {
                    result = SumarNumeros(valor, result);
                }
            }
            return result;
        }

        private static int SumarNumeros(string valor, int result)
        {
            int variable = int.Parse(valor);
            if (variable < 0)
                throw new ArgumentException("negatives not allowed");
            result += variable;
            return result;
        }

        private static string[] GetCadenas(string cadena, List<char> limitador)
        {
            if (cadena.StartsWith(ARGUMENT))
            {
                limitador.Add(Convert.ToChar(cadena.Substring(1,1)));
                cadena = cadena.Substring(2);
            }

            return cadena.Split(limitador.ToArray());
        }
    }
}
