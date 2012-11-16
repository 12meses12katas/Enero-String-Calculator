$:.unshift File.join( File.dirname( __FILE__ ), %w[.. .. lib] )


require 'test/unit'
require 'string_calculator'


class TC_StringCalculator < Test::Unit::TestCase
 
 def test_add

    assert_equal( StringCalculator.add( "" ), 0 )
    assert_equal( StringCalculator.add( "1" ), 0 )
    assert_equal( StringCalculator.add( "1,\n" ), 0 )

    assert_equal( StringCalculator.add( "1,2" ), 3 )
    
    assert_equal( StringCalculator.add( "// \n1 2 3 4 5 6 7 8 9" ), 45 )

    assert_equal( StringCalculator.add( "2, "*10 ), 20 )

    assert_equal( StringCalculator.add( "//;\n2; 3;4;50" ), 59 )

    assert_raise( NegativeNumbersNotAllowed ) { StringCalculator.add( "//.\n2.3.4.-5" ) }
  end
end