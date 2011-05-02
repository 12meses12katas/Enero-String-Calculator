import groovy.util.GroovyTestCase;

class StringCalculatorTest extends GroovyTestCase{
  
  StringCalculator stringCalculator = new StringCalculator()
  
  void testAdd1(){
    assert stringCalculator.add("") == 0
    assert stringCalculator.add("0") == 0
    assert stringCalculator.add("3") == 3
    assert stringCalculator.add("4,5") == 9
    assert stringCalculator.add("3,7") == 10
    assert stringCalculator.add("0,70") == 70
    assert stringCalculator.add("56,0") == 56
    assert stringCalculator.add("56,45") == 101
  }
  
  void testAdd2(){
    // Definimos una cantidad desconocida de números
    int n = (int)(Math.random() * 1000)
    def lista = []
    def acumulador = 0
    n.times{
      // Generamos números al azar entre 0 y 100
      int x = (int)(Math.random() * 100)
      lista << x
      // Mientras en el acumulador llevamos la cuenta del total de la suma
      acumulador += x
    }
    // Generamos el String de números
    String numbers = lista.join(",")
    // Corroboramos que el resultado del acumulador sea igual al de la ejecución del método
    assert stringCalculator.add(numbers) == acumulador
  }
  
  void testAdd3(){
    assert stringCalculator.add("1\n2,3") == 6
    assert stringCalculator.add("1\n2,3\n4\n5,6\n7,8,9") == 45
  }
  
  void testAdd4(){
    assert stringCalculator.add("//;\n1;2") == 3
    assert stringCalculator.add("//!\n1!2!3!4!5!6!7!8!9") == 45
  }
  
  void testAdd5(){
    shouldFail(Exception){
      stringCalculator.add("//;\n1;-2")
    }
    shouldFail(Exception){
      stringCalculator.add("//;\n-1;2") == 3
    }
    shouldFail(Exception){
      stringCalculator.add("//!1\n2!3!-4\n5!-6!7\n8!-9") == 45
    }
    try{
      stringCalculator.add("//!\n1!2!3!-4\n5!-6!7\n8!-9") == 45
    }catch(Exception e){
      println "\n" + e.message
    }
  }
 
  void testAdd6(){
    assert stringCalculator.add("//;\n1;2;3000;4000") == 3
    assert stringCalculator.add("//!\n1001!1!2!3001!3!4001!4!5!6!7!8!9") == 45
  }
  
  void testAdd7(){
    assert stringCalculator.add("//[!!!]\n1!!!2!!!3000!!!4000") == 3
    assert stringCalculator.add("//[!!!]\n1001!!!1!!!2!!!3001!!!3!!!4001!!!4!!!5!!!6!!!7!!!8!!!9") == 45
  }
  
}