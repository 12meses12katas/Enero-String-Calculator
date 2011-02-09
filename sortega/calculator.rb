def add(numbers)
  lines = numbers.split(/\n/)

  if lines[0] =~ /^\/\//
    delimiter = lines.shift[2,2]
  else
    delimiter = ','
  end

  total = 0
  lines.each do |line|
    line.split(/#{delimiter}/).each do |n|
      value = n.to_i
      if value < 0
        raise ArgumentError.new('negatives not allowed')
      else
        total += value
      end
    end
  end

  return total
end
