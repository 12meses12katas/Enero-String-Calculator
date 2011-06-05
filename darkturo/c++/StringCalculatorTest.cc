#include "StringCalculator.h"
#include <cppunit/TestCaller.h>
#include <cppunit/TestAssert.h>
#include <cppunit/TestFixture.h>
#include <cppunit/TextTestRunner.h>
#include <cppunit/extensions/HelperMacros.h>

class StringCalculatorTest : public CppUnit::TestFixture {
    CPPUNIT_TEST_SUITE(StringCalculatorTest);
    CPPUNIT_TEST(test_add_with_empty_string);
    CPPUNIT_TEST(test_add_with_one_number);
    CPPUNIT_TEST(test_add_with_two_numbers);
    CPPUNIT_TEST(test_add_with_unknown_amount_of_numbers);
    CPPUNIT_TEST(test_add_with_new_lines_between_numbers);
    CPPUNIT_TEST(test_add_with_a_new_different_delimiter);
    CPPUNIT_TEST_EXCEPTION(test_add_negatives_not_allowed_throw_exception, \
                           StringCalculatorException);
    CPPUNIT_TEST(test_add_negatives_not_allowed_check_message);
    CPPUNIT_TEST(test_add_negatives_not_allowed_check_message_multiple_negs);
    CPPUNIT_TEST_SUITE_END();
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

    void test_add_with_one_number() {
        CPPUNIT_ASSERT_EQUAL( 1, calc->add("1"));
    }
    
    void test_add_with_two_numbers() {
        CPPUNIT_ASSERT_EQUAL( 3, calc->add("1,2"));
    }

    void test_add_with_unknown_amount_of_numbers() {
        CPPUNIT_ASSERT_EQUAL(15, calc->add("1,2,3,4,5"));
    }
    
    void test_add_with_new_lines_between_numbers() {
        CPPUNIT_ASSERT_EQUAL(6, calc->add("1\n2,3"));
    }

    void test_add_with_a_new_different_delimiter() {
        CPPUNIT_ASSERT_EQUAL(3, calc->add("//;\n1;2"));
    }

    void test_add_negatives_not_allowed_throw_exception() {
        CPPUNIT_ASSERT_EQUAL( 3, calc->add("1,-2"));
    }

    void test_add_negatives_not_allowed_check_message() {
        try {
            calc->add("1,-2");
        } catch (StringCalculatorException &except) {
            string expected_message= "negatives not allowed: -2";
            CPPUNIT_ASSERT_EQUAL(0, expected_message.compare(except.what()));
        }
    }

    void test_add_negatives_not_allowed_check_message_multiple_negs() {
        try {
            calc->add("1,-2,-3,4,5,6,-7");
        } catch (StringCalculatorException &except) {
            string expected_message= "negatives not allowed: -2, -3, -7";
            CPPUNIT_ASSERT_EQUAL(0, expected_message.compare(except.what()));
        }
    }
};


int main(int argc, char **argv) {
    CppUnit::TextTestRunner runner;

    CPPUNIT_TEST_SUITE_REGISTRATION(StringCalculatorTest);
    runner.addTest( CppUnit::TestFactoryRegistry::getRegistry().makeTest() );
    runner.run();
}
