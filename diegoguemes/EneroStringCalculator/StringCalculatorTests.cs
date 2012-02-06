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
    }
}