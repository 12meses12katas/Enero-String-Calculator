using System;
using System.Collections.Generic;

namespace KataStringCalculator
{
    public class StringCalculator
    {
        private const String SEPARADOR_DEFECTO = ",";
        private const String SEPARADOR_DEFECTO_2 = "\n";
        private const int VALOR_DEFECTO = 0;
        private const string SEPARADOR_INICIAL = "//[";
        private const string SEPARADOR_FINAL = "]";
        private const int NUMERO_MAXIMO = 1000;
        
        public int Add(string numbers)
        {
            List<String> separadores = RecuperaSeparadores(numbers);
            numbers = LimpeaDefinicionSeparadorOpcional(numbers, separadores);
                
            return Suma(numbers, separadores);
        }

        private int Suma(string cadena, List<String> separadores)
        {
            int resultado = VALOR_DEFECTO;
            foreach (string number in cadena.Split(separadores.ToArray(), StringSplitOptions.RemoveEmptyEntries))
            {
                int numero = Convert.ToInt32(number);
                if (ValidarNumeros(Convert.ToInt32(numero)))
                {
                    resultado += numero;
                }
            }
            return resultado;
        }

        private bool ValidarNumeros(int numero)
        {
            bool resultado = true;
            if (numero < 0)
            {
                throw new ArgumentException("Negatives not allowed");
            }
            if (numero > NUMERO_MAXIMO)
            {
                resultado = false;
            }
            return resultado;
        }
        private List<String> RecuperaSeparadores(string cadena)
        {
            List<String> separadores = new List<String>();
            separadores.Add(SEPARADOR_DEFECTO);
            separadores.Add(SEPARADOR_DEFECTO_2);

            string[] separadorOpcional = RecuperaDelimitadorOpcional(cadena);
            separadores.AddRange(separadorOpcional);

            return separadores;
        }

        private string LimpeaDefinicionSeparadorOpcional(string cadena, List<String> separadores)
        {

            foreach(string separador in separadores)
            {
                cadena = cadena.Replace(SEPARADOR_INICIAL + separador, "").Replace(SEPARADOR_FINAL, "").Replace("["+separador,"");
            }
            return cadena;
        }

        public string[] RecuperaDelimitadorOpcional(string cadena)
        {
            if (cadena.StartsWith(SEPARADOR_INICIAL))
            {
                string[] parametros = cadena.Split('\n');
                string parteConDelimitador = parametros[0];
                parteConDelimitador = parteConDelimitador.Replace(SEPARADOR_INICIAL, "");
                parteConDelimitador = parteConDelimitador.Replace(SEPARADOR_FINAL, "");

                string[] delimitadores = parteConDelimitador.Split('[');

                return delimitadores;
                
            }
            return null;
        }

    }
}
