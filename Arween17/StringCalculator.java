/****************************************************************************************
 * Clase StringCalculator
 * Kata del mes de enero de la página web:
 * https://github.com/12meses12katas/Enero-String-Calculator
 * Clase que suma una serie de números a partir de un String. El String puede tener
 * diferentes formatos. Hay que separar los números del String para sumarlos, pueden  
 * estar separados por diferentes separadores.
 * @author: Mayte Bravo Gutiérrez
 ***************************************************************************************/

/* Clases para manejar los números negativos, cuando se introduce un número negativo
 * debe ser ignorado y además hay que imprimir todos los negativos introducidos.
 */

class NumeroNegativo extends Exception{

    NumeroNegativo(String mensaje){
        super(mensaje);
    }
}

class Validar{
    public void validarNumero(int x) throws NumeroNegativo{
        if (x<0)
            throw new NumeroNegativo("No puedes introducir números negativos.");
    }
}

// Clase principal
public class StringCalculator {

    static int add(String num){
        int posicionSeparadorInicial=0,posicionSeparadorFinal;  // Posiciones de los separadores a lo largo del String.
        int caracteresTotales=num.length();  // Caracteres totales del String.
        int acumulador=0;  // Variable auxiliar para sumar los numeros.
        String operando1,operando2="";  // Variables que contienen los numeros del String para ser sumados.
	String separador;  // Separador que se utiliza cuando la cadena empieza por //.
        Validar validar=new Validar();  // Variable para manejar las excepciones de números negativos.
	int [] numeros;  // Vector con los números introducidos, para comprobar cuales son negativos.
	int comas=0;  // Variable que se utiliza para conocer cuántos números se han introducido.
	int contador=1;  // Contador para introducir los números en el vectos numeros.
	int numeroSeparadores=0;  // Número de separadores cuando se introduce más de un separador en las cadenas que empiezan por //.
	String [] separadores;  // Vector que contiene los diferentes separadores de las cadenas que comienzan por //.

	/* Aquí comprobamos si la cadena empieza por '//[' o por '//', algunos ejemplos de String
	 * que empiecen por estos caracteres:
	 * //;\n25;3;105;44      //<-->\n3<-->28<-->49<-->35
	 * Los String anteriores utilizan el separador que hay entre // y \n
	 * //[*][%]\n145*90%180%215*328%23      //[<->][$][;_;]\n325<->25$63;_;510$30;_;43<->70<->
 	 * Los String anteriores utilizan los separadores que hay entre []
	 */
	if (num.indexOf("//[")!=-1){
	   for (int i=0;i<num.indexOf("\n");i++){
	      if (num.substring(i,i+1).equals("]")){
		 numeroSeparadores++;  // Número de separadores diferentes
	      }
	   }
  	   separadores=new String[numeroSeparadores];
	   posicionSeparadorInicial=num.indexOf("[");
	   posicionSeparadorFinal=num.indexOf("]");
	   for (int i=0;i<separadores.length;i++){
              separadores[i]=num.substring(posicionSeparadorInicial+1,posicionSeparadorFinal);
	      posicionSeparadorInicial=posicionSeparadorFinal+1;
	      posicionSeparadorFinal=num.indexOf("]",posicionSeparadorInicial+1);
	   }
	   num=num.substring(num.indexOf("\n")+1,caracteresTotales);
	   for (int i=0;i<separadores.length;i++){
	      for (int j=0;j<num.length();j++){
		 num=num.replace(separadores[i],",");  // Sustituimos los separadores por ','
	      }
	   }
	} else if (num.indexOf("//")!=-1){
            separador=num.substring(2,num.indexOf("\n"));
            num=num.substring(num.indexOf("\n")+1,caracteresTotales);
	    num=num.replace(separador,",");  // Sustituimos el separador por ','
        }
 
	for (int i=0;i<num.length();i++){
	   if ((num.substring(i,i+1).equals(",")) || (num.substring(i,i+1).equals("\n"))){
	      comas++;  // Utilizamos las comas para saber cuántos números hay.
	   }
	}
        numeros=new int[comas+1];  // Este vector se utiliza para ver los números negativos.

	/* Cuando llegamos a este punto el String está formado sólo por números separados por
	 * comas o '\n'. Aquí se "sacan" los números del String, se suman, se comprueban si
	 * son negativos y cuáles son mayores de 1000.
	 */
        if (num.length()==0){
	   System.out.print("Numeros introducidos: "+num+"  ---  ");
           return 0;  // Un String vacío devuelve 0.
	} else if ((num.indexOf(",")==-1) && (num.indexOf("\n")==-1)){
            try{	    
		System.out.print("Numeros introducidos: "+num+"  ---  ");
                validar.validarNumero(Integer.parseInt(num));
		if (Integer.parseInt(num)>1000)
		   num="0";
		// Si el String está formado por un sólo número se devuelve este número.
		return Integer.parseInt(num);
            } catch (NumeroNegativo n){
                System.out.println(n.getMessage());
		System.out.println("Numero negativo: "+num);
		return -1;
            }
        } else {
	    num=num.replace("\n",",");  // Reemplazamos los \n por comas.
	    System.out.print("Numeros introducidos: "+num+"  ---  ");
	    caracteresTotales=num.length();
            posicionSeparadorInicial=num.indexOf(",");
            operando1=num.substring(0,posicionSeparadorInicial);
	    if (Integer.parseInt(operando1)>1000)
	       operando1="0";  //Si el número es mayor que 1000 se ignora.
            acumulador=Integer.parseInt(operando1);
	    numeros[0]=acumulador;
            posicionSeparadorFinal=num.indexOf(",",posicionSeparadorInicial+1);
	    // A continuación se van sacando los operandos y se van sumando.
            while (posicionSeparadorFinal!=-1){
               operando2=num.substring(posicionSeparadorInicial+1,posicionSeparadorFinal);
	       if (Integer.parseInt(operando2)>1000)
		  operando2="0";
	       numeros[contador]=Integer.parseInt(operando2);
               acumulador=acumulador+Integer.parseInt(operando2);
               posicionSeparadorInicial=posicionSeparadorFinal;
               posicionSeparadorFinal=num.indexOf(",",posicionSeparadorInicial+1);
	       contador++;
            }
            operando2=num.substring(posicionSeparadorInicial+1,caracteresTotales);
	    if (Integer.parseInt(operando2)>1000)
	       operando2="0";
	    numeros[contador]=Integer.parseInt(operando2);
	    // Aquí comprobamos cuáles de los números introducidos son negativos.
	    try{
  	       for (int i=0;i<numeros.length;i++){
	   	  validar.validarNumero(numeros[i]);
	       }
	    } catch (NumeroNegativo n){
	       System.out.println(n.getMessage());
	       for (int i=0;i<numeros.length;i++){
		   if (numeros[i]<0){
		      System.out.print("Numero negativo: "+numeros[i]+" ");
		   }
		}
		System.out.println();
		return -1;
            }
	    return (acumulador+Integer.parseInt(operando2));
	}
    }

    public static void main(String args[]){
	System.out.println("Resultado con '': "+add(""));
        System.out.println("Resultado con '12': "+add("12"));
	System.out.println("Resultado con '20,30': "+add("20,30"));
	System.out.println("Resultado con '100,50,20,5,15,60': "+add("100,50,20,5,15,60"));
	System.out.println("Resultado con '100\\n20,30\\n25,300': "+add("100\n20,30\n25,300"));
	System.out.println("Resultado con '//;\\n150;300;25;75;90': "+add("//;\n150;300;25;75;90"));
	System.out.println("Resultado con '//;-;\\n40;-;30;-;25;-;200;-;115': "+add("//;-;\n40;-;30;-;25;-;200;-;115"));
	System.out.println("Resultado con '-15': "+add("-15"));
	System.out.println("Resultado con '-2,3,9,-10,-125,15': "+add("-2,3,9,-10,-125,15"));
	System.out.println("Resultado con '-5\\n3,7\\n-14,22\\n-16': "+add("-5\n3,7\n-14,22\n-16"));
	System.out.println("Resultado con '1,-2\\n3,-4\\n5,-6\\n7': "+add("1,-2\n3,-4\n5,-6\n7"));
	System.out.println("Resultado con '//<-->\\n10<-->-29<-->30<-->-27<-->14<-->-7': "+add("//<-->\n10<-->-29<-->30<-->-27<-->14<-->-7"));
	System.out.println("Resultado con numeros mayores que 1000 --> '1050': "+add("1050"));
	System.out.println("Resultado con numeros mayores que 1000 --> '1250,300,2000,14': "+add("1250,300,2000,14"));
	System.out.println("Resultado con numeros mayores que 1000 --> '3,15\\n27,3700\\n,1001\\n300\\n8000': "+add("3,15\n27,3700\n1001\n300\n8000"));
	System.out.println("Resultado con '//[;][_]\\n120;200_105;25_10': "+add("//[;][_]\n120;200_105;25_10"));
	System.out.println("Resultado con '//[;-;][$][<-->]\\n10;-;20$30<-->40;-;50<-->60$70': "+add("//[;-;][$][<-->]\n10;-;20$30<-->40;-;50<-->60$70"));
	System.out.println("Resultado con '//[<>][%]\\n-10<>20%105<>100': "+add("//[<>][%]\n-10<>20%105<>-100"));
	System.out.println("Resultado con '//[%-%][*]\\n1005*14%-%33*40*1250%-%999': "+add("//[%-%][*]\n1005*14%-%33*40*1250%-%999"));
    }
}

