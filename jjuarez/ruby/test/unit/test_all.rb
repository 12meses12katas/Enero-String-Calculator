require 'test/unit/testsuite'
require 'test/unit/ui/console/testrunner'
require 'tc_string_calculator'


class TS_StringCalculator
  
  def self.suite
    
    suite = Test::Unit::TestSuite.new
    
    suite << TC_StringCalculator.suite

    return suite
  end
end


Test::Unit::UI::Console::TestRunner.run( TS_StringCalculator )
