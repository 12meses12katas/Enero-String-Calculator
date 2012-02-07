using System;
using NUnit.Framework;

namespace EneroStringCalculator
{
    [TestFixture]
    public class StringCalculatorTests
    {
        private StringCalculator calculator;
        [SetUp]
        public void SetUp()
        {
            calculator = new StringCalculator(new NumberSplitter());
        }

        [Test]
        public void AddingEmptyStringShouldReturnZero()
        {
            Assert.AreEqual(0, calculator.Add(""));
        }

        [Test]
        public void AddingSingleNumberShouldReturnTheNumber()
        {
            Assert.AreEqual(5, calculator.Add("5"));
        }

        [Test]
        public void AddWithTwoNumbers()
        {
            Assert.AreEqual(15, calculator.Add("5,10"));
        }

        [Test]
        public void AddWithMultipleNumbers()
        {
            Assert.AreEqual(50, calculator.Add("2,3,5,10,30"));
        }

        [Test]
        public void AllowAddWithNewLineSeparator()
        {
            Assert.AreEqual(10, calculator.Add("2\n3,5"));
        }

        [Test]
        public void AllowAddWithCustomSeparator()
        {
            Assert.AreEqual(10, calculator.Add("//;\n2;3,5"));
        }

        [Test]
        [ExpectedException(typeof(ArgumentException))]
        public void AddingNegativesThrowsArgumentException()
        {
            calculator.Add("-10,1,2,3");
        }

        [Test]
        public void AddingNegativesThrowsArgumentExceptionContainingNegatives()
        {
            try
            {
                calculator.Add("-10,-20,1,2,3");
                Assert.Fail("Argument exception should have been thrown");
            }
            catch (ArgumentException exception)
            {
                Assert.True(exception.Message.EndsWith("-10,-20"));
            }
        }
    }
}