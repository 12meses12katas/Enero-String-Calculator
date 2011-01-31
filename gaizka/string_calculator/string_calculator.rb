
# Ejercicios 1 y 2
class StringCalculator_version1_and_2
  def add(string)
    string.split(",").map{|n| n.to_i}.inject(0) do |acc, item|
      acc + item
    end
  end
end

# Ejercicio 3
class StringCalculator_version_3
  def add(string)
    return 0 if string.nil? || string.empty?

    string.split(/[,\n]/).map{|n| n.to_i}.inject(0) do |acc, item|
      acc + item
    end
  end
end

# Válido para todos
class StringCalculator

  DefaultDelimiter = /[,\n]/

  def add(string)
    return 0 if string.nil? || string.empty?
    
    delimiter = extract_delimiter!(string) || DefaultDelimiter

    integers = string.split(delimiter).map{|n| n.to_i}

    if ! (negatives = integers.select{|i| i < 0}).empty?
      raise StandardError.new "negatives not allowed: #{negatives.join(",")}"
    end
    
    integers.inject(0) do |acc, item|
      acc + (item <= 1000 ? item : 0)
    end
  end
  
  private
  def extract_delimiter!(string)

    # No header
    return nil unless string.slice! %r{^//(.+)\n}

    # 1 char case: $1 now is
    return $1 if $1.size == 1

    # What to do if header is: "//[\n" ??
    
    # multiple [**][,][zzz] delimiters
    delimiters = $1.scan(/\[([^\]]+)\]/).flatten

    # Build a new regexp joining them: **|,|zzz . Escape special chars!!
    Regexp.new(delimiters.map{|d| Regexp.escape(d)}.join("|"))
  end
end
