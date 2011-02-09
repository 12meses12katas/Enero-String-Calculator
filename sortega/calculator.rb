def add(numbers)
  lines = numbers.split(/\n/)

  if lines[0] =~ /^\/\//
    delimiter = lines.shift[2,2]
  else
    delimiter = ','
  end

  total = 0
  lines.each do |line|
    line.split(/#{delimiter}/).each { |n| total += n.to_i }
  end

  return total
end
