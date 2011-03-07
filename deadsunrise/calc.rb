class Calc

  def add(operation)
    return 0 if operation == ''
    @clean_operands = clean(operation)
    calculate(@clean_operands)
  end

  private

  def clean(operation)
    operands = replace_delimiters(operation)
    check_for_negative_operands?(operands)
    reject_big(operands)
    return operands
  end


  def replace_delimiters(operation)
    delimiters = parse_delimiter(operation)
    operands = operation.gsub(/\/\/.*\n/ , '')
    delimiters.flatten.each { |d| operands.gsub!(d.to_s,',') }
    operands.split(',').map! {|s| s.to_i }
  end

  def parse_delimiter(operation)
    delimiters = ["\n"]
    delimiters << operation.scan(/\[([^[]+)\]/)
    delimiters << operation.scan(/\/\/(.)\n/)
  end

  def check_for_negative_operands?(operands)
    negatives = operands.reject {|i| i >= 0  }
    raise("negatives not allowed #{negatives.join(',')}") unless negatives.empty?
  end


  def reject_big(operands)
    operands.reject! {|i| i > 1000}
  end


  def calculate(operands)
    operands.reduce(:+)
  end

end
