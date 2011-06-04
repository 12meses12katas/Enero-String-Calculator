#include "StringCalculator.h"
#include <cppunit/TestCaller.h>
#include <cppunit/TestAssert.h>
#include <cppunit/TestFixture.h>
#include <cppunit/TextTestRunner.h>

class StringCalculatorTest : public CppUnit::TestFixture {
    private:
        StringCalculator * calc;
    public:
        void setUp() {
            calc = new StringCalculator();
        }
        
        void tearDown() {
            delete calc;
        }

    void test_add_with_empty_string() {
        CPPUNIT_ASSERT_EQUAL( 0, calc->add(""));
    }
};

int main(int argc, char **argv) {
    CppUnit::TextTestRunner runner;
    runner.addTest( new CppUnit::TestCaller<StringCalculatorTest>(
                        "test_add_with_empty_string",
                        &StringCalculatorTest::test_add_with_empty_string));
    runner.run();
}
