import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

/*
* Félix López
* To test  I've used http://www.scalatest.org/getting_started_with_flat_spec
*/
class AddSpec extends FlatSpec with ShouldMatchers {
    /*
    * 
    */
    def add(operation:String):Int = {
        val regex ="""(-?\d)+""".r                                                
        var sum = 0              
        if (operation == "") {             
            return 0
        }
        val numbers = regex.findAllIn(operation) 
        sum = numbers.toList.foldLeft(0)((sum, value) => {
            if (value.toInt < 0) throw new Exception("Negative number")
            if (value.toInt > 1000) throw new Exception("Number too big")
            sum + value.toInt 
        })
        return sum
    }

  "Add" should "sum the numbers in the string" in {
    add("") should equal (0)
    add("1") should equal (1)
    add("1,2") should equal (3)
    add("1,2,3,4,5,6") should equal (21)
    add("1\\n2\n3") should equal (6)
    add("100;[]200/*32") should equal (332)
  }

  it should "throw Exception if a number is negative or > 1000" in {
    evaluating { add("-10,2") } should produce [Exception]
    evaluating { add("1001,2") } should produce [Exception]
  }
}

