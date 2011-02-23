class StringCalculator

  DEFAULT_DELIMITER = ","

  def add(string)
    return 0 if string.empty?
    cleaned_string = parse_string(string)
    addends = extract_addends(cleaned_string)
    addends.reduce(:+)
  end # add

  def parse_string(cad)
    new_string = cad
    if cad[0] == "\\" then
      delimiters = extract_delimiters(cad[1..cad.index("\n")-1])
      new_string = cad[cad.index("\n")+1..cad.length-1]
      delimiters.each { |de| new_string.gsub!(de,DEFAULT_DELIMITER) }
      #new_string = new_string.gsub!(cad[1],DEFAULT_DELIMITER)
    end
    new_string = new_string.gsub("\n", DEFAULT_DELIMITER)
  end

  def extract_delimiters(cad)
    return cad.gsub("][", " ").gsub(/[\[,\]]/, "").split
  end

  def extract_addends(cad)
    working_array = cad.split(DEFAULT_DELIMITER).collect { |a| a.to_i }
    negatives = []
    working_array.each { |wa| negatives << wa if wa < 0 }
    working_array.delete_if { |wa| wa > 999 }
    if !negatives.empty?
      raise "No negatives allowed: #{negatives.join(', ')}" if !negatives.empty?
    end
    return working_array
  end

end # class