module StringCalculator where


import Control.Exception
import Data.Char (isDigit)
import Data.List (isPrefixOf)
import Data.Typeable
import Prelude hiding (catch)

{-
5) Calling Add with a negative number will throw an exception “negatives not allowed” 
	and the negative that was passed.if there are multiple negatives, show all of them in the exception message
-}

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
			toks = if hasDelimiterDefinitions nums then deleteDelimiterDefinitions nums else nums
			delimiters = if hasDelimiterDefinitions nums then extractDelimiters nums else "\n," -- PROVISIONAL
			numTokens = tokens (\c -> isDigit c && (and $ map (\del -> c /= del) delimiters)) toks
			control = if null numTokens then "" else last numTokens

-- pure

toDigit :: String -> Int
toDigit = read

equalStrings :: String -> String -> Bool
equalStrings first second = 
	if length first == length second
		then and $ zipWith (==) first second
		else False

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
			
hasDelimiterDefinitions :: String -> Bool
hasDelimiterDefinitions = isPrefixOf "//"

deleteDelimiterDefinitions :: String -> String
deleteDelimiterDefinitions str = withoutDelimiters
								where
									withoutSlashes = dropWhile (== '/') str
									withoutDelimiters = dropWhile (\c -> not $ isDigit c) withoutSlashes
									-- TODO "//[\n]...\n"

-- TODO
extractDelimiters :: String -> String
extractDelimiters str = concat $ dropWhile (\s -> length s > 1) $ delimTokens (\c -> c /= '[' && c /= ']') delims
	where
		delims =  takeWhile (/= '\n') $ dropWhile (== '/') str

delimTokens :: (Char -> Bool) -> String -> [String]
delimTokens prop text = 
	if null token
		then []
		else [token] ++ delimTokens prop nextTokens
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
			putStr "Test add with an empty String:\t\t\t"
			if testAddWithEmptyString then putStrLn "+ Passed +" else putStrLn "- Failed -"
			putStrLn ""
			putStrLn "STRING = \"1,1,3,7,5,4\""
			putStr "Test add with a well formed String:\t\t"
			if testAddWithWellFormedString then putStrLn "+ Passed +" else putStrLn "- Failed -"
			putStrLn ""
			putStrLn "STRING = \"1,\n2,3,4,5\""
			putStr "Test add with a bad formed String:\t\t"
			if testAddWithBadFormedString then putStrLn "+ Passed +" else putStrLn "- Failed -"
			putStrLn ""
			putStrLn "STRING = \"//[,][:][;][*]\n1,2:3;4*5\""
			putStr "Test add with a user defined delimiters:\t"
			if testAddWithUserDefinedDelimiters then putStrLn "+ Passed +" else putStrLn "- Failed -"
			putStrLn ""
			

testAddWithEmptyString :: Bool
testAddWithEmptyString = add "" == 0

testAddWithWellFormedString :: Bool
testAddWithWellFormedString = add "1,1,3,7,5,4" == 21

testAddWithBadFormedString :: Bool
testAddWithBadFormedString = add "1,\n2,3,4,5" == Prelude.minBound

testAddWithUserDefinedDelimiters :: Bool
testAddWithUserDefinedDelimiters = add "//[,][:][;][*]\n1,2:3;4*5" == 15