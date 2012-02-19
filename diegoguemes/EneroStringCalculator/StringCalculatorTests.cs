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
        public void Adding_Empty_String_Should_Return_Zero()
        {
            Assert.AreEqual(0, calculator.Add(""));
        }

        [Test]
        public void Adding_Single_Number_Should_Return_TheNumber()
        {
            Assert.AreEqual(5, calculator.Add("5"));
        }

        [Test]
        public void Add_With_Two_Numbers()
        {
            Assert.AreEqual(15, calculator.Add("5,10"));
        }

        [Test]
        public void Add_With_Multiple_Numbers()
        {
            Assert.AreEqual(50, calculator.Add("2,3,5,10,30"));
        }

        [Test]
        public void Allow_Add_With_New_Line_Separator()
        {
            Assert.AreEqual(10, calculator.Add("2\n3,5"));
        }

        [Test]
        public void Allow_Add_With_Custom_Separator()
        {
            Assert.AreEqual(10, calculator.Add("//;\n2;3,5"));
        }

        [Test]
        [ExpectedException(typeof(ArgumentException))]
        public void Adding_Negatives_Throws_Argument_Exception()
        {
            calculator.Add("-10,1,2,3");
        }

        [Test]
        public void Adding_Negatives_Throws_Argument_Exception_Containing_Negatives()
        {
            try
            {
                calculator.Add("-10,-20,1,2,3");
                Assert.Fail("Argument exception should have been thrown");
            }
            catch (ArgumentException exception)
            {
                Assert.True(exception.Message.Contains("-10,-20"));
            }
        }

        [Test]
        public void Ignore_Numbers_Greater_Than_One_Thousand()
        {
            Assert.AreEqual(1010, calculator.Add("2,3,5,1000,1001"));
        }

        [Test]
        public void Allow_Add_With_Custom_Separator_Of_Any_Length()
        {
            Assert.AreEqual(10, calculator.Add("//[***]\n2,3***5"));
        }

        [Test] 
        public void Allow_Add_With_Multiple_Custom_Separators()
        {
            Assert.AreEqual(10, calculator.Add("//[***][###]\n2###3***5"));
        }


    }
}