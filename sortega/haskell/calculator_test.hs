module Calculator_Test where

import Test.HUnit
import qualified Control.Exception as E
import Calculator ( add )

test_empty_is_0 = TestCase $ assertEqual
    "empty is 0" (Just 0) (add "")

test_simple_addition = TestCase $ assertEqual
    "simple addition" [Just 2, Just 7] [add "1,1", add "1,4,2"]

test_multiple_numbers = TestCase $ assertEqual
    "multiple numbers" (Just 5050) ( add $ 
	"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16," ++
	"17,18,19,20,21,22,23,24,25,26,27,28,29," ++
	"30,31,32,33,34,35,36,37,38,39,40,41,42," ++
	"43,44,45,46,47,48,49,50,51,52,53,54,55," ++
	"56,57,58,59,60,61,62,63,64,65,66,67,68," ++
	"69,70,71,72,73,74,75,76,77,78,79,80,81," ++
	"82,83,84,85,86,87,88,89,90,91,92,93,94," ++
	"95,96,97,98,99,100" )

test_newlines = TestCase $ assertEqual
    "newlines" (Just 6) ( add "1\n2,3" )

test_custom_delimiter = TestCase $ assertEqual
    "custom delimiter" (Just 6) ( add "//;\n1\n2;3" )

test_reject_negatives = TestCase $ assertEqual
    "error on negatives" Nothing (add "1,3,-1,4")

test_ignore_greater_than_1000 = TestCase $ assertEqual
    "ignore > 1000" [Just 2, Just 1002] [add "1001,2", add "1000,2"]

test_multicharacter_delimiter = TestCase $ assertEqual
    "multicharacter delimiter" (Just 9) (add "//[***]\n1\n2***3***3")

test_multiple_delimiter = TestCase $ assertEqual
    "multiple delimiter" [Just 9, Just 9] 
    [add "//[***][%]\n1\n2***3%3", add "//[***][->]\n1\n2***3->3"]

tests = TestList [
    TestLabel "test empty is 0" test_empty_is_0,
    TestLabel "simple addition" test_simple_addition,
    TestLabel "multiple numbers" test_multiple_numbers,
    TestLabel "newlines" test_newlines,
    TestLabel "custom delimiter" test_custom_delimiter,
    TestLabel "error on negatives" test_reject_negatives,
    TestLabel "ignore > 1000" test_ignore_greater_than_1000,
    TestLabel "multicharacter delimiter" test_multicharacter_delimiter,
    TestLabel "multiple delimiter" test_multiple_delimiter
    ]

main = runTestTT tests
