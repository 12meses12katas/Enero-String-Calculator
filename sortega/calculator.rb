def add(numbers)
  total = 0
  numbers.split(/,|\n/).each { |n| total += n.to_i }
  return total
end
