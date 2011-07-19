class NegativeNumbersNotAllowed < StandardError
end

class StringCalculator

  def set_delimiters(string)
    return @delimiters = ["\n",","] unless string.start_with?("//[")    
    @delimiters = ["\n",","].push *string.match(/\/\/\[(.+)\]/)[1].split('][')
  end


  def add(string = '')
    return 0 if string.empty?
    set_delimiters(string)
    nums = string.split(/\]\n/).last.split(/#{@delimiters.join('|')}/).map(&:to_i)
    negatives = nums.select{ |num| num < 0 }

    raise NegativeNumbersNotAllowed.new(negatives) if negatives.any?

    nums.select{|num| (1..1000) === num }.reduce(&:+) || 0
  end
end