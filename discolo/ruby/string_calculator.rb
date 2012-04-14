PATTERN = /\/\/\[(.+)\]\n(.*)/

def getPattern(string)
  pattern = /,|\n/
  if (string.start_with?("//"))
    pattern = string[PATTERN,1].gsub("][","|")
  end
  return pattern
end

def getValid(num)
  raise 'Numbers must be positive' unless num >= 0
  if num < 1000
    return num
  else
    return 0
  end
end

def add (snumbers)
  return 0 unless snumbers != ""
  numbers = 0
  data = snumbers
  if snumbers.start_with?("//")
	data = snumbers[PATTERN,2] 
  end
  data.split(getPattern(snumbers)).each do |num|
    numbers+=getValid(num.to_i)
  end
  return numbers
end
