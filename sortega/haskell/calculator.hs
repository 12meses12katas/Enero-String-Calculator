module Calculator ( add ) where

import Text.Regex.Posix
import Text.Regex ( mkRegex, splitRegex )

add :: String -> Maybe Int
add input = addDelim delims numbers
    where (delims, numbers) = parseDelimiters input

addDelim :: [String] -> String -> Maybe Int
addDelim _ [] = Just 0
addDelim delims input = 
    case negatives of
	[] -> Just (sum valid_numbers)
	_  -> Nothing
    where numbers = map read $ splitRegex regex input
	  valid_numbers = filter ((>=) 1000) numbers
	  negatives = filter ((>=) 0) numbers
          regex = mkRegex $ foldl (\accum delim -> accum ++ '|' : delim)
		            "\n" delims

parseDelimiters :: String -> ([String], String)
parseDelimiters input = (delims, numbers)
    where (_, delimSpec, numbers) = input =~ "^//([^\\n]*)\\n" :: (String, String, String)
	  delims = case delimSpec of
	    '/':'/':rest -> splitDelims rest
	    _ -> [","]

splitDelims :: String -> [String]
splitDelims [] = []
splitDelims input
    | delim == [] = splitDelims rest
    | otherwise   = delim : splitDelims rest
    where (delim, _, rest) = input =~ "\\[|\\]+" :: (String, String, String)
		
escape :: Char -> String
escape c | c `elem` regexChars = '\\' : [c]
         | otherwise = [c]
    where regexChars = "\\+*?()^$.{}]|["
