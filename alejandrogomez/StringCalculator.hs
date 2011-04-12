module StringCalculator where


import Control.Exception
import Data.Char (isDigit)
import Data.Typeable
import Prelude hiding (catch)


-- not pure

-- returns minimum value with a bad formed String
add :: String -> Int
add = sum . numbers

numbers :: String -> [Int]
numbers nums = 
		if equalStrings control "BadInput"
			then [Prelude.minBound]
			else map toDigit numTokens
		where
			numTokens = tokens (\c -> c /= '\n' && c /= ',' && isDigit c) nums
			control = if null numTokens then "" else last numTokens

-- pure

toDigit :: String -> Int
toDigit = read

equalStrings :: String -> String -> Bool
equalStrings first second = 
	if length first == length second
		then and $ zipWith (==) first second
		else False

-- TODO : 3).2
tokens :: (Char -> Bool) -> String -> [String]
tokens _ [] = []
tokens prop text =
	if length text - length clean > 1							-- we have more than a single delimiter here
		then ["BadInput"] 										-- the input is NOT ok
		else
			if null token
				then [] 
				else token : tokens prop nextTokens
					where
					clean = dropWhile (not . prop) text
					token = takeWhile prop clean
					nextTokens = dropWhile prop clean
							

-- test

testAdd :: IO ()
testAdd = do
			putStrLn "**********************"
			putStrLn "TEST STRING CALCULATOR"
			putStrLn "**********************"
			putStrLn ""
			putStrLn ""
			putStrLn "STRING = \"\""
			putStr "Test add with an empty String:\t\t"
			if testAddWithEmptyString then putStrLn "+ Passed +" else putStrLn "- Failed -"
			putStrLn ""
			putStrLn "STRING = \"1,1,3,7,5,4\""
			putStr "Test add with a well formed String:\t"
			if testAddWithWellFormedString then putStrLn "+ Passed +" else putStrLn "- Failed -"
			putStrLn ""
			putStrLn "STRING = \"1,\n2,3,4,5"
			putStr "Test add with a bad formed String:\t"
			if testAddWithBadFormedString then putStrLn "+ Passed +" else putStrLn "- Failed -"
			putStrLn ""

testAddWithEmptyString :: Bool
testAddWithEmptyString = add "" == 0

testAddWithWellFormedString :: Bool
testAddWithWellFormedString = add "1,1,3,7,5,4" == 21

testAddWithBadFormedString :: Bool
testAddWithBadFormedString = add "1,\n2,3,4,5" == Prelude.minBound