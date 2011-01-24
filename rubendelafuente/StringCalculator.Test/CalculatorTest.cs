using System;
using NUnit.Framework;

namespace StringCalculator.Test
{
    [TestFixture]
    public class CalculatorTest
    {
        [Test]
        public void SiPasamosStringVacioDevuelveCero()
        {
            int result = Calculator.Add(string.Empty);
            Assert.AreEqual(0,result);
        }

        [Test]
        public void SiPasamosUnoDevuelveUno()
        {
            int result = Calculator.Add("1");
            Assert.AreEqual(1,result);
        }
        [Test]
        public void SiPasamosDosDevuelveDos()
        {
            int result = Calculator.Add("2");
            Assert.AreEqual(2, result);
        }
        [Test]
        public void SiPasamosUnoComaDosDevuelveTres()
        {
            int result = Calculator.Add("1,2");
            Assert.AreEqual(3, result);
        }

        [Test]
        public void SiPasamosUnoComaDosComaTresDevuelveSeis()
        {
            int result = Calculator.Add("1,2,3");
            Assert.AreEqual(6, result);
        }

        [Test]
        public void SiPasamosUnoSaltoDeLineaDosComaTresDevuelveSeis()
        {
            int result = Calculator.Add("1\n2,3");
            Assert.AreEqual(6, result);
        }

        [Test]
        public void SiPamosUnoPuntoYComaDosDevuelveTres()
        {
            int result = Calculator.Add("\\;\n1;2");
            Assert.AreEqual(3, result);
        }

        [Test]
        [ExpectedException(typeof(ArgumentException), ExpectedMessage = "negatives not allowed")]
        public void SiSumamosNumeroNegativoDeuvelveException()
        {
            int result = Calculator.Add("-1");
        }
    }
}
