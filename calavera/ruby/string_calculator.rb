class StringCalculator
  def calculate(string)
    return 0 if string.nil? || string.empty?

    delimiter = look_for_delimiter(string)

    array = string.split(delimiter).
      map {|num| num.to_i}.
      select {|num| num <= 1000}

    negatives = array.select {|num| num < 0}
    raise "negatives not allowed: #{negatives.inspect}" unless negatives.empty?

    array.inject(0) {|sum, num| sum += num}
  end

  private
  def look_for_delimiter(string)
    header = string.slice!(%r{^//(.+)\n})
    return %r{\n|,} unless header

    scan = $1.scan(%r{(?<=\[)[^\]]+(?=\])})
    escape = scan.collect {|delim| Regexp.escape(delim)}.join("|")
    Regexp.new(escape)
  end
end
