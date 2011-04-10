#code 
  
  def add(numbers)
    
    origin=numbers#   just to remember the string given
    if ((numbers.split("\n"))[0].to_s)[0,2]=="//"#  Check if there's a first line with // in it
      delimiters=numbers.split("\n")[0].to_s.split("//")[1].to_s#  obtain the delimiters line without //
      delimiters=delimiters.split("[").join("").split("]")#  split the delimiters
      if delimiters.length > 1 #prevent an empty delimiter from appearing
        delimiters.delete(-1)
      end
      numbers=numbers.split("\n")[1].to_s# get the numbers line
    else
      delimiters=[",","\n"]# otherwise set deafult delmiters
    end
    
    sum=0
    divided=Array.new
    divided << numbers #prepare an array for splitting
    
    #split all the elements of the array with each delimiter
    
    delimiters.each do |d|
      thistime=Array.new
      divided.each do |s|
        s2=s.split(d)
        s2.each do |ss|
          thistime << ss
        end
      end
      divided=thistime #puts back the splitted elements into the array for another pass, or for adding
    end
    error=false
    negative=Array.new
    divided.each do |d|
      if (d.to_i)<0  #collect negative numbers
        negative << d
        error=true
      end
      if (d.to_i<=1000) #filter numbers over a thousand
      sum+=d.to_i
      end
    end
    if error
      raise "negative numbers found: "+negative.join(",")
    end
    
    return sum.to_s+" from "+origin.to_s
  
  end
  
#testing

puts "result should be 0, and is: "+add("").to_s
puts "result should be 1, and is: "+add("1").to_s
puts "result should be 3, and is: "+add("1,2").to_s
puts "result should be 7, and is: "+add("3,4").to_s
puts "result should be 12, and is: "+add("3\n4,5").to_s
puts "result should be 20, and is: "+add("//[;][,]\n4;5;6,5").to_s
puts "result should be 15, and is: "+add("//[;]\n4;5;6").to_s
puts "result should be 20, and is: "+add("//;\n4;5;6;5").to_s
puts "result should be 1003, and is: "+add("1000,1001,3").to_s
puts "result should be 20, and is: "+add("3,4,5,8").to_s
puts "result should be 20, and is: "+add("//***\n3***4***5***8").to_s
puts "result should be 20, and is: "+add("//[***][ee]\n3***4ee5***8").to_s
puts "next one should raise an error"
puts "result should be an error, and is: "+add("-3,4,-5,8").to_s