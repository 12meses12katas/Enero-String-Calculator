package kata.stringcalculator

import org.specs.runner.JUnitSuiteRunner
import org.specs.util.DataTables
import org.junit.runner.RunWith
import org.specs._
import org.specs.runner.{ ConsoleRunner, JUnit4 }

@RunWith(classOf[JUnitSuiteRunner])
class CalculatorSpecTest extends JUnit4(CalculatorSpec)

object CalculatorSpec extends Specification with DataTables {
  "This calculator" should {
    "return 0 with empty string" in {
      calculator.add("") must_== 0
    }
    
    "return the same number with only one number" in {
    	"numbers" | "sum" |> 
    	"1" ! 1 |
    	"2" ! 2 |
    	"3" ! 3 |
    	"4" ! 4 | { 
    		(n, s) => { calculator.add(n) must_== s } 
    	} 
    }
    
    "return the sum with two numbers" in {
    	"numbers" | "sum" |> 
    	"1,1" ! 2 |
    	"2,4" ! 6 |
    	"3,10" ! 13 |
    	"4,40" ! 44 | { 
    		(n, s) => { calculator.add(n) must_== s } 
    	} 
    }
    
    "return the sum of any number of arguments" in {
    	"numbers"       | "sum" |> 
    	"1,1"           ! 2    |
    	"2,4,6"         ! 12   |
    	"3,10,100,1000" ! 1113 |
    	"1,"*79 + "1"   ! 80   | { 
    		(n, s) => { calculator.add(n) must_== s } 
    	}
    }
    
    "allow newlines and commas" in {
    	"numbers"         | "sum" |> 
    	"1,1"             ! 2    |
    	"2,4\n6"          ! 12   |
    	"3\n10,100\n1000" ! 1113 |
    	"1\n40"           ! 41   | { 
    		(n, s) => { calculator.add(n) must_== s } 
    	}
    }
    
    "allow custom delimiters of any length" in {
    	"numbers"         | "sum" |> 
    	"//;\n1;2"        ! 3     |
    	"//[***]\n1***2"    ! 3     |{ 
    		(n:String, s:Int) => { calculator.add(n) must_== s } 
    	}
    }
    
    "allow multiple delimiters of any length" in {
    	"numbers"             | "sum" |> 
    	"//[*][%]\n1*2%3"     ! 6     |
    	"//[**][%%]\n1**2%%3" ! 6     |{ 
    		(n:String, s:Int) => { calculator.add(n) must_== s } 
    	}
    }
    
    "do not allow negatives" in {
    	calculator.add("-1") must throwA[NegativesNotAllowed] 
    }
    
    "numbers > 1000 should be ignored" in {
    	"numbers"         | "sum" |> 
    	"2,1001"          ! 2     |
    	"2,1001,2"        ! 4     | {
    		(n, s) => { calculator.add(n) must be equalTo(s) }
    	}
    }
  }
}
