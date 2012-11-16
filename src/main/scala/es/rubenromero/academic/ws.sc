package es.rubenromero.academic


import es.rubenromero.academic.katas.StringCalculator._

object ws {
	val P1 = "//(.*)\n(.*)".r                 //> P1  : scala.util.matching.Regex = //(.*)
                                                  //| (.*)
  val x = "//;\n1;2;4;5"                          //> x  : java.lang.String = //;
                                                  //| 1;2;4;5
  P1 unapplySeq x                                 //> res0: Option[List[String]] = Some(List(;, 1;2;4;5))
  add("2,-2\n2")                                  //> java.lang.Error: negatives are not allowed
                                                  //| 	at es.rubenromero.academic.katas.StringCalculator$.addWithDelim(StringCa
                                                  //| lculator.scala:13)
                                                  //| 	at es.rubenromero.academic.katas.StringCalculator$.add(StringCalculator.
                                                  //| scala:22)
                                                  //| 	at es.rubenromero.academic.ws$$anonfun$main$1.apply$mcV$sp(es.rubenromer
                                                  //| o.academic.ws.scala:10)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at es.rubenromero.academic.ws$.main(es.rubenromero.academic.ws.scala:6)
                                                  //| 
                                                  //| 	at es.rubenromero.academic.ws.main(es.rubenromero.academic.ws.scala)
}