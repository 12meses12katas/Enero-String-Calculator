module Calculator ( add ) where

import Data.List.Split ( splitOneOf )

add :: String -> Maybe Int
add input = addDelim delimiter numbers
    where (delimiter, numbers) = parseDelimiter input

addDelim :: Char -> String -> Maybe Int
addDelim _ [] = Just 0
addDelim delim input = 
    case negatives of
	[] -> Just (sum numbers)
	_  -> Nothing
    where delims = delim : "\n"
	  numbers = filter ((>=) 1000) $ map read $ splitOneOf delims input
	  negatives = filter ((>=) 0) numbers

parseDelimiter :: String -> (Char, String)
parseDelimiter input = case input of
    '/':'/':delim:'\n':numbers -> (delim, numbers)
    _ -> (',', input)
