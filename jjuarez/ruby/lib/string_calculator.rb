class NegativeNumbersNotAllowed < StandardError
end


class StringCalculator

  DEFAULT_DELIMITER = ','
  
  private
  def self.extract_numbers( arguments )
  
    d = arguments.match( /\/\/(?<d>\D{1})$/ ) { |m| m[:d] }
    
    sl = d ? arguments.split( "\n" )[1].split( d ) : arguments.split( DEFAULT_DELIMITER )
    nl = []
    
    sl.each { |c| nl << c.to_i if c =~/\d/ }
    nl
  end
  
  public
  def self.add( arguments )
    
    return 0 if arguments.empty?
    
    numbers = extract_numbers( arguments )

    return 0 if( numbers.length == 0 || numbers.length == 1 )
    
    negatives = []
    sum       = 0
    
    numbers.each do |i| 

      if( i < 0 )
        negatives << i
      elsif( i <= 1000 )
        sum += i
      end
    end
    
    if negatives.length > 0
      raise NegativeNumbersNotAllowed.new( negatives )
    else
      sum
    end
  end
end