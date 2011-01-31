class Calc

  def add(operation)
    return 0 if operation == ''
    @delimiter = parse_delimiter(operation)
    @clean_operation = parse(operation)
    calculate(@clean_operation)
  end

  private

  def parse(operation)
    operands = operation.gsub(/\/\/.\n/ , '').gsub("\n",',').split(@delimiter)
    reject_big(operands)
    negative_operands?(operands)
    operands.map! {|s| s.to_i }
  end


  def parse_delimiter(operation)
    delimiter = ','
    if operation[0..1] == "//"
      delimiter = operation[2..2]
    end
    delimiter
  end


  def negative_operands?(operands)
    negatives = operands.reject {|i| i.to_i >= 0  }
    raise("negatives not allowed #{negatives.join(',')}") unless negatives.empty?
  end


  def reject_big(operands)
    operands.reject! {|i| i.to_i > 1000}
  end


  def calculate(operands)
    operands.reduce(:+)
  end

end
