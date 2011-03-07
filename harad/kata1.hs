add :: String -> Int
add "" = 0
add ('/':'/':separator:'\n':ys) = foldl (\acc x -> acc + read x) 0 (tokens ys separator)
add xs = add ("//,\n" ++ xs)

tokens xs separator
	| head xs == '-' && separator /= '-' =
		let func x = (if x=="" then " " else '-':(head x):[])
		in error ("Negatives not allowed:" ++
		cfoldl (\acc x -> acc ++ func x) "" (tokens xs  '-'))
	| ',' `notElem` xs &&  '\n' `notElem` xs && separator `notElem` xs = [xs]
	| otherwise = let 
	(x,y) = span (\x -> if x /= ',' && x /= '\n' && x /= separator then True else False) xs 
	in x:tokens (tail y) separator