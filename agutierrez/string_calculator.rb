class StringCalculator

  DEFAULT_DELIMITER = ","

  def add(raw_string)
    return 0 if raw_string.empty?
    cleaned_string = clean_string(raw_string)
    addends = extract_addends(cleaned_string)
    addends.reduce(:+)
  end # add

  def clean_string(dirty_string)
    new_string = dirty_string
    if dirty_string[0] == "\\" then
      delimiters = extract_delimiters(dirty_string[1..dirty_string.index("\n")-1])
      new_string = dirty_string[dirty_string.index("\n")+1..dirty_string.length-1]
      delimiters.each { |de| new_string.gsub!(de,DEFAULT_DELIMITER) }
    end
    new_string = new_string.gsub("\n", DEFAULT_DELIMITER)
  end

  def extract_delimiters(delimiter_string)
    return delimiter_string.gsub("][", " ").gsub(/[\[,\]]/, "").split
  end

  def extract_addends(addends_string)
    working_array = addends_string.split(DEFAULT_DELIMITER).collect { |a| a.to_i }
    negatives = []
    working_array.each { |wa| negatives << wa if wa < 0 }
    working_array.delete_if { |wa| wa > 999 }
    raise "No negatives allowed: #{negatives.join(', ')}" if !negatives.empty?
    return working_array
  end

end # class