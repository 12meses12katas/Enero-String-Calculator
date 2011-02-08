def add(numbers)
  total = 0
  numbers.split(',').each { |n| total += n.to_i }
  return total
end

add("")
add("1")
add("1,2")
