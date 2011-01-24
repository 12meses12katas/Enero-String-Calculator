using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StringCalculator
{
	public class Calculator
	{
		/// <summary>
		/// Suma la los valores de la cadena.
		/// </summary>
		/// <param name="value">Cadena con los valores a sumar</param>
		/// <returns></returns>
		public int Add(string value)
		{
			try
			{
				return GetValues(value).Sum();
			}
			catch
			{
				throw;
			}
			
		}

		/// <summary>
		/// Devuelve una lista con los numeros a sumar.
		/// </summary>
		/// <param name="values">Cadena de valores</param>
		private List<int> GetValues(string values)
		{
			var retVal = new List<int>();
			var negativeValues = new List<int>();

			string[] numbers = values.Split(GetDelimiters(values).ToArray(), StringSplitOptions.RemoveEmptyEntries);

			for (int x = 0; x < numbers.Length; x++)
			{
				int number = CheckNumber(numbers[x]);

				if(number >= 0)
					retVal.Add(number);
				else
					negativeValues.Add(number);
			}

			if(negativeValues.Count > 0)
				throw new Exception(GetNegativeValuesMessage(negativeValues));

			return retVal;
		}

		/// <summary>
		/// Devuelve el mensaje de la exception con los valores negativos.
		/// </summary>
		/// <param name="values">Lista de valores</param>
		/// <returns></returns>
		private string GetNegativeValuesMessage(List<int> values)
		{

			string retVal = "Negative values not allowed: ";

			values.ForEach(x=> retVal += x + " ");

			return retVal.Trim();
		}


		/// <summary>
		/// Comprueba que el valor sea un número válido.
		/// </summary>
		/// <param name="value">Valor a comprobar</param>
		/// <returns></returns>
		private int CheckNumber(string value)
		{
			int retVal = 0;

			Int32.TryParse(value, out retVal);

			return (retVal>1000) ? 0 : retVal;
		}

		/// <summary>
		/// Devuelve la lista de delimitadores.
		/// </summary>
		/// <param name="values">Cadena de valores</param>
		/// <returns></returns>
		private List<string> GetDelimiters(string values)
		{
			var retVal = new List<string>() { ",", "\n" };

			if (values.IndexOf("//") == 0)
				retVal.AddRange(values.Split(new char[] {'[', ']'} ));

			return retVal;
		}
	}
}
