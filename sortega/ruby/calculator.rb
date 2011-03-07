def add(numbers)
  return 0 unless numbers != nil
  lines = numbers.split(/\n/)

  if lines[0] =~ /^\/\//
    delimiter_spec = lines.shift[2..-1]
    delimiters = delimiter_spec.split(/(\[|\])+/)
    delimiters.select { |e| e }
  else
    delimiters = [ ',' ]
  end
  del_regex = (delimiters.map {|del| Regexp.escape(del) }).join('|')

  total = 0
  lines.each do |line|
    line.split(/#{del_regex}/).each do |n|
      value = n.to_i
      if value < 0
        raise ArgumentError.new('negatives not allowed')
      elsif value <= 1000
        total += value
      end
    end
  end

  return total
end
