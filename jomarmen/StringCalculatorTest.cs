using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using NUnit.Framework;


namespace KataStringCalculator
{
    [TestFixture]
    public class StringCalcultorTests
    {
        
        [Test]
        public void TestEmpty()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(0, sc.Add(""));
            
        }

        [Test]
        public void Test1N()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(1, sc.Add("1"));
            
        }

        [Test]
        public void Test2N()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(3, sc.Add("1,2"));
        }

        [Test]
        public void TestNParametros()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(10,sc.Add("1,2,3,4"));
        }

        [Test]
        public void TestMultipleSeparador()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(15, sc.Add("1\n2,3\n4,5"));
        }

        [Test]
        public void TestSuperMultipleSeparador()
        {
            StringCalculator sc = new StringCalculator();
            string parametro = "";
            int resultado = 0;
            for (int i = 0; i < 100; i++)
            {
                string separador = ",";
                if ((i % 2) == 2)
                {
                    separador = "\n";
                }
                if (i == 0)
                {
                    parametro += i;
                }
                else
                {
                    parametro += separador + i;
                }

                resultado += i;

                Assert.AreEqual(resultado, sc.Add(parametro));
            }
        }

        [Test]
        public void TestConDelimitadorOpcional()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(10, sc.Add("//[$]\n1,2\n3$4"));
        }

        [Test, ExpectedException(typeof(ArgumentException), ExpectedMessage = "Negatives not allowed")]
        public void TestExceptionNumerosNegativos()
        {
            StringCalculator sc = new StringCalculator();
            sc.Add("//[$]\n1,2\n-3$4");
        }

        [Test]
        public void TestIgnorarNumerosSuperioresAMil()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(7, sc.Add("//[$]\n1,2\n1001$4"));
        }
        [Test]
        public void TestDelimitadoresLargo()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(6, sc.Add("//[***]\n1***2***3"));
        }

        [Test]
        public void TestVariosDelimitadores()
        {
            StringCalculator sc = new StringCalculator();
            Assert.AreEqual(6, sc.Add("//[*][%]\n1*2%3"));
        }
    }
}
