using System;
using NUnit.Framework;
using StringCalculator;

namespace StringCalculatorTest
{
	public class CalculatorTest
	{
		Calculator calc;

		[SetUp]
		public void SetUp()
		{
			calc = new Calculator();
		}

		[Test]
		public void AddWithoutParameters()
		{
			int result = calc.Add(String.Empty);
			Assert.AreEqual(0, result);
		}

		[Test]
		public void AddOneParameter()
		{
			int result = calc.Add("1");
			Assert.AreEqual(1, result);
		}

		[Test]
		public void AddTwoParameters()
		{
			int result = calc.Add("1,2");
			Assert.AreEqual(3, result);
		}

		[Test]
		public void AddManyParameters()
		{
			int result = calc.Add("1,2,3,4");
			Assert.AreEqual(10, result);
		}

		[Test]
		public void AddParametersWithNewLines()
		{
			int result = calc.Add("1\n2,3");
			Assert.AreEqual(6, result);
		}

		//Se modifica el formato del delimitador a //[delimitador] para adaptarlo al punto 7
		[Test]
		public void AddWithCustomDelimiter()
		{
			int result = calc.Add("//[;]\n1;2");
			Assert.AreEqual(3, result);
		}

		[Test]
		[ExpectedException("System.Exception", ExpectedMessage="Negative values not allowed: -1 -3")]
		public void AddNegativeParameters()
		{
			int result = calc.Add("-1,2,-3");
			Assert.Fail("Negative values not allowed: -1 -3");
		}

		[Test]
		public void AddNumberBiggerThan1000()
		{
			int result = calc.Add("2, 1001");
			Assert.AreEqual(2, result);
		}

		[Test]
		public void AddWithAnyLengthDelimiter()
		{
			int result = calc.Add("//[***]\n1***2***3");
			Assert.AreEqual(6, result);
		}

		[Test]
		public void AddMultipleCustomDelimiters()
		{
			int result = calc.Add("//[*][%]\n1*2%3");
			Assert.AreEqual(6, result);
		}

		[Test]
		public void AddMultipleDelimitersWithManyCharacters()
		{
			int result = calc.Add("//[*$][%=]\n1*$2%=3");
			Assert.AreEqual(6, result);
		}
	}
}
