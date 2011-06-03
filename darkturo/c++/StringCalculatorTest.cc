#include "StringCalculator.h"
#include <cppunit/TestSuite.h>
#include <cppunit/TestResult.h>
#include <cppunit/TestCaller.h>
#include <cppunit/TestFixture.h>
#include <cppunit/ui/text/TextTestRunner.h>

class StringCalculatorTest : public CppUnit::TestFixture {
    private:
        StringCalculator * calc;
    public
    void setUp() {
        calc = new StringCalculator();
    }
    
    void tearDown() {
        delete calc;
    }

    void test_add_with_empty_string() {
        CPPUNIT_ASSERT( 0  == calc.add("") )
    }
}

int main(int argc, char **argv) {
    CppUnit::TextUi::TestRunner runner;
    runner.addTest( new CppUnit::TestCaller<>(
}
