use Test;
use string_calculator;

BEGIN {
    plan( tests => 13 )
}

ok (0, add(""), "Test with empty string");

ok (1, add("1"), "Test with one number");

ok (3, add("1,2"), "Test call with two numbers");

ok (6, add("1,2,3"), "Unknown amount of numbers");

ok (15, add("1,2,3,4,5"), "Unknown amount of numbers second try");

ok (6, add("1\n2,3"), "new lines between numbers");

ok (3, add("//;\n1;2"), "change delimiter to ';'");

ok ("negatives not allowed: -1", add("-1"), 
    "negatives not allowed, only one negative");

ok ("negatives not allowed: -1, -3, -5", add("-1,2,-3,4,-5"), 
    "negatives not allowed, only one negative");

ok (2, add("2,1001"), "Numbers bigger than 1000 should be ignored");

ok (6, add("//[***]\n1***2***3"), "Delimiters can be of any length");

ok (6, add("//[*][%]\n1*2%3"), "Allow multiple delimiters");

ok (15, add("//[***][;;;][##]\n1***2;;;3;;;4##5"), 
    "handle multiple delimiters with length longer than one char")
