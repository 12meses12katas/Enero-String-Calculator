using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;

namespace StringCalculator
{
   [TestFixture]
    public class CalcultatorTester
    {
       Calculator calculator;

       [SetUp]
       public void setup()
       {
           calculator = new Calculator();
       }
       
       [Test]
       public void addingEmptyStringIsZero()
       {
           int actual = calculator.Add("");
           int expected = 0;

           Assert.AreEqual(expected, actual, "Empty string should be 0");
       }

       [Test]
       public void addingOneNumber()
       {
           int actual = calculator.Add("0");
           int expected = 0;

           Assert.AreEqual(expected, actual, "One number in addend should be that number in result");

           actual = calculator.Add("765");
           expected = 765;

           Assert.AreEqual(expected, actual, "One number in addend should be that number in result");
       
       }

       [Test]
       public void addingTwoNumbersSeparetedByComa()
       {
           int actual = calculator.Add("1,0");
           int expected = 1;

           Assert.AreEqual(expected, actual, "Addition of two numbers separated by coma");

           actual = calculator.Add("765,1");
           expected = 766;

           Assert.AreEqual(expected, actual, "Addition of two numbers separated by coma");

       }

       [Test]
       public void addingAnyNumbersSeparetedByComa()
       {
          
           int actual = calculator.Add("1,1,1,1,1,1,1,1,1,1,1,1,1");
           int expected = 13;

           Assert.AreEqual(expected, actual, "Addition of any numbers separated by coma");

       }

       [Test]
       public void addingNumbersSeparetedByComaOrNewLine()
       {

           int actual = calculator.Add("1,1\n1");
           int expected = 3;

           Assert.AreEqual(expected, actual, "Possible delimiters: coma or new line");

       }

       [Test]
       public void addingNumbersWithCustomDelimiter()
       {
           string customDelimiter = ";";
           int actual = calculator.Add(string.Format("//[{0}]\n1{0}2", customDelimiter));
           int expected = 3;

           Assert.AreEqual(expected, actual, "Support custom delimiter");

       }

       [Test]
       [ExpectedException(typeof(ArgumentException))]
       public void addingNegativeNumbersThrowException()
       {
           int actual = calculator.Add("5,-4");
          
       }

       [Test]
       public void addingNegativeNumbersExceptionInformsAboutNumbers()
       {
           try
           {
               int actual = calculator.Add("15,-4,-2");
           }
           catch (ArgumentException ex)
           {
               Assert.IsTrue(ex.Message.Contains("-4"));
               Assert.IsTrue(ex.Message.Contains("-2"));
         
           }
       }


       [Test]
       public void addingNumbersOver1000AreIgnored()
       {
           int actual = calculator.Add("1000,1");
           int expected = 1001;

           Assert.AreEqual(expected, actual, "Adding numbers over 100 are ignored");

           actual = calculator.Add("1001,1");
           expected = 1;

           Assert.AreEqual(expected, actual, "Adding numbers over 100 are ignored");
       
       }

       [Test]
       public void addingNumbersWithAnyLengthCustomDelimiter()
       {
           string customDelimiter = "hell03610";
           int actual = calculator.Add(string.Format("//[{0}]\n1{0}2", customDelimiter));
           int expected = 3;

           Assert.AreEqual(expected, actual, "Support any length custom delimiter");

       }

       [Test]
       public void addingNumbersWithSeveralCustomDelimiter()
       {
           string aCustomDelimiter = "hell03610";
           string anotherCustomDelimiter = "elmendalerenda";
           
           int actual = calculator.Add(string.Format("//[{0}][{1}]\n0{0}6{1}9{0}0", aCustomDelimiter, anotherCustomDelimiter));
           int expected = 15;

           Assert.AreEqual(expected, actual, "Support serveral custom delimiters");

       }

    }
}
