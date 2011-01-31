using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using StringCalculator;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace StringCalculatorTest
{
    /// <summary>
    /// Summary description for StringCalculatorTest
    /// </summary>
    [TestClass]
    public class StringCalculatorTest
    {
        StringCalculator.StringCalculator calculator = new StringCalculator.StringCalculator();
        Dictionary<int, string> inputValues = new Dictionary<int, string>();

        public StringCalculatorTest()
        {
            inputValues.Add(0, "0");
            inputValues.Add(1, "1");
            inputValues.Add(10, "10");
        }

        private TestContext testContextInstance;

        public TestContext TestContext
        {
            get{ return testContextInstance;}
            set{ testContextInstance = value;}
        }

        #region Additional test attributes
        //
        // You can use the following additional attributes as you write your tests:
        //
        // Use ClassInitialize to run code before running the first test in the class
        // [ClassInitialize()]
        // public static void MyClassInitialize(TestContext testContext) { }
        //
        // Use ClassCleanup to run code after all tests in a class have run
        // [ClassCleanup()]
        // public static void MyClassCleanup() { }
        //
        // Use TestInitialize to run code before running each test 
        // [TestInitialize()]
        // public void MyTestInitialize() { }
        //
        // Use TestCleanup to run code after each test has run
        // [TestCleanup()]
        // public void MyTestCleanup() { }
        //
        #endregion

        [TestMethod]
        public void AdditionWithUniqueParameterReturnsParameter()
        {
            foreach (var item in inputValues.Keys)
            {
                int expected = item;
                int actual =calculator.Add(inputValues[item]);
                Assert.AreEqual(expected, actual);
            }
        }

        [TestMethod]
        public void AdditionWithUniqueStringEmptyReturnsZero()
        {
            Assert.AreEqual(0, calculator.Add(""));
        }

        [TestMethod]
        public void AdditionWithoutTwoParametersReturnsResult()
        {
            Assert.AreEqual(2, calculator.Add("1,1"));
            Assert.AreEqual(0, calculator.Add("0,0"));
            Assert.AreEqual(11, calculator.Add("1,10"));
            Assert.AreEqual(1, calculator.Add("1,0"));
            Assert.AreEqual(5, calculator.Add("5,0"));
        }

        [TestMethod]
        public void AdditionWithThreeParametersReturnsResult()
        {
            Assert.AreEqual(2, calculator.Add("1,1,0"));
            Assert.AreEqual(0, calculator.Add("0,0,0"));
            Assert.AreEqual(11, calculator.Add("1,10,0"));
            Assert.AreEqual(1, calculator.Add("1,0,0"));
            Assert.AreEqual(5, calculator.Add("5,0,0"));
        }

        [TestMethod]
        public void AdditionWithFourParametersReturnsResult()
        {
            Assert.AreEqual(2, calculator.Add("1,1,0,0"));
            Assert.AreEqual(0, calculator.Add("0,0,0,0"));
            Assert.AreEqual(11, calculator.Add("1,10,0,0"));
            Assert.AreEqual(1, calculator.Add("1,0,0,0"));
            Assert.AreEqual(5, calculator.Add("5,0,0,0"));
        }

        [TestMethod]
        public void ParseOperatorsWithCommasAndNewLines()
        {
            Assert.AreEqual(2, calculator.Add("1,1\n0"));
            Assert.AreEqual(0, calculator.Add("0\n0,0"));
            Assert.AreEqual(11, calculator.Add("1,10\n0"));
            Assert.AreEqual(1, calculator.Add("1\n0\n0"));
            Assert.AreEqual(5, calculator.Add("5\n0\n0"));
        }

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void TwoDelimitatorsTogetherNotAllowed()
        {
            calculator.Add("1,\n0");
            calculator.Add(",\n");
        }

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void NegativeOperatorsNotAllowed()
        {
            calculator.Add("1,-1");
        }

        [TestMethod]
        public void NumbersBiggerThan1000ShouldBeIgnored()
        {
            Assert.AreEqual(1001, calculator.Add("1,1000"));
            Assert.AreEqual(0, calculator.Add("0,1100"));
            Assert.AreEqual(1, calculator.Add("1,1001"));
            Assert.AreEqual(1, calculator.Add("1,1001"));
            Assert.AreEqual(1004, calculator.Add("5,999"));
        }

        [TestMethod]
        public void RecognizesSimpleCorrectHeader()
        {
            Assert.AreEqual(1001, calculator.Add("//[;]\n1;1000"));
        }

        [TestMethod]
        [ExpectedException(typeof(Exception))]
        public void DetectsErrorInDelimitator()
        {
            calculator.Add("//[;]\n1,1000");
        }

        [TestMethod]
        public void RecognizesLongDelimitators()
        {
            Assert.AreEqual(6, calculator.Add("//[***]\n1***2***3"));
        }

        [TestMethod]
        public void RecognizesMultipleSingleDelimitators()
        {
            Assert.AreEqual(6, calculator.Add("//[,][;]\n1,2;3"));
        }

        [TestMethod]
        public void RecognizesMultipleLongDelimitators()
        {
            Assert.AreEqual(6, calculator.Add("//[,,,][;;;]\n1,,,2;;;3"));
        }
    }
}
