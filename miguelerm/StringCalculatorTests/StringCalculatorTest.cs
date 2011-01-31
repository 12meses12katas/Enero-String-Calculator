using NUnit.Framework;
using System;
namespace Mike.Katas.Tests
{
	[TestFixture()]
	public class StringCalculatorTest
	{
		[Test()]
		public void CadenaVaciaRetornaCero ()
		{
			
			int resultado = StringCalculator.Add("");
			
			Assert.AreEqual(0, resultado);
			
		}
		
		[Test()]
		public void IngresarUnSoloNumeroRetornaElMismoNumero()
		{
			
			int resultado = StringCalculator.Add("1");
			
			Assert.AreEqual(1, resultado);

		}
		
		[Test()]
		public void DosNumerosSeparadosPorComaSeSuman()
		{
			int resultado = StringCalculator.Add("1,2");
			
			Assert.AreEqual(3, resultado);
		}
		
		[Test()]
		public void VariosNumerosSeparadosPorComaSeSuman()
		{
			int resultado = StringCalculator.Add("1,2,3");
			
			Assert.AreEqual(6, resultado);
		}

		[Test()]
		public void SePuedenSepararLosNumerosConUnaNuevaLinea()
		{
			int resultado = StringCalculator.Add("1\n2,3");
			
			Assert.AreEqual(6, resultado);
		}
		
		[Test()]
		public void SePuedeModificarElSeparadorDeNumeros()
		{
			
			int resultado = StringCalculator.Add("//;\n1;2");
			
			Assert.AreEqual(3, resultado);
			
		}
		
		[Test()]
		public void NuevaLineaDespuesDelSeparadorEsOpcional()
		{
			
			int resultado = StringCalculator.Add("//;1;2");
			
			Assert.AreEqual(3, resultado);
			
		}
		
		[Test()]
		[ExpectedException(typeof(InvalidOperationException), ExpectedMessage="negatives not allowed [-2, -1]")]
		public void NoSePermiteSumarNumerosNegativos()
		{
			
			StringCalculator.Add("-2,-1,0,1,2");
			
			Assert.Fail();
			
		}
		
		[Test()]
		public void SeIgnoranLosNumerosMayoresAMil()
		{
			
			int resultado = StringCalculator.Add("//+2+1001");
			
			Assert.AreEqual(2, resultado);
			
		}
		
		[Test()]
		public void SePuedenUtilizarSeparadoresDeCualquierLongitud()
		{
			
			int resultado = StringCalculator.Add("//[***]\n1***2***3");
			
			Assert.AreEqual(6, resultado);
			
		}
		
		[Test()]
		public void SePuedenUtilizarVariosSeparadores()
		{
			
			int resultado = StringCalculator.Add("//[*][%]\n1*2%3");
			
			Assert.AreEqual(6, resultado);
			
		}
		
		[Test()]
		public void SePuedenUtilizarVariosSeparadoresDeCualquierLongitud()
		{
			
			int resultado = StringCalculator.Add("//[*][%%][&$#]\n1*2%%3&$#4");
			
			Assert.AreEqual(10, resultado);
						
		}
		
		
	}
}








