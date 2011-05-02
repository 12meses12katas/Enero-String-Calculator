class StringCalculator{
  //Firma de método para las tareas 1,2 y 3
  //int add(String numbers, String delimiter = ";"){
  //Firma de método para la tarea 4
  int add(String numbers){
    //Código para tareas 1 y 2
    //def arrayNumbers = numbers.split(',')
    //Código para tarea 3
    //def arrayNumbers = numbers.split(',|\n')
    //Código para tarea 3
    def delimitador = ','
    if(numbers.startsWith("//") && numbers.charAt(3) == "\n"){
      delimitador = numbers.charAt(2)
    }else if(numbers.startsWith("//") && numbers.charAt(2) == "["){
      def cierre = numbers.indexOf("]")
      if(numbers.charAt(cierre + 1) == "\n")
        delimitador = numbers.substring(3,cierre)
      println "DELIMITADOR:" + delimitador
    }
    def arrayNumbers = numbers.split("^//|${delimitador}|\n|\[|\]")
    //Código para tarea 5
    def negatives  = arrayNumbers.findAll { Integer.valueOf(it ?: 0 ) < 0 }
    if(negatives)
      throw new Exception("negatives not allowed: ${negatives.join(',')}")
    //arrayNumbers.collect{ Integer.valueOf(it ?: 0 ) }.sum()
    // Código para tarea 6
    arrayNumbers.collect{ 
      def number = Integer.valueOf(it ?: 0 ) 
      number < 1000 ? number : 0
    }.sum()
  }
}
