#code 
  
  def add(numbers)
    
    origin=numbers #          just to remember the string given
 
    if ((numbers.split("\n"))[0].to_s)[0,2]=="//" #      check if there is a first line with // in it
      delimiters=numbers.split("\n")[0].to_s.split("//")[1].to_s #  obtain the delimiters line without //
      delimiters=delimiters.split("[").join("").split("]")   # separe the delimiters
      
    if delimiters.length > 1  #   prevent an empty delimiter from appearing
        delimiters.delete(-1)
      end

      numbers=numbers.split("\n")[1].to_s  #   get the number line

    else
      delimiters=[",","\n"]   #  otherwise, set deafault delimiters
    end
    
    sum=0
    divided=Array.new
    divided << numbers   #prepare an array for dividing
    
#      divide all the elemnts of the array with each delimiter

    delimiters.each do |d|
      thistime=Array.new
      divided.each do |s|
        s2=s.split(d)
        s2.each do |ss|
          thistime << ss
        end
      end
      divided=thistime  #puts back the divided elemnts into the original array for another process or for addition
    end
    
    divided.each do |d|
      if (d.to_i>0)&&(d.to_i <= 1000)  #check for negative or for numbers over a thousand
      sum+=d.to_i
      end
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
puts "result should be 12, and is: "+add("-3,4,-5,8").to_s
puts "result should be 12, and is: "+add("//***\n-3***4***-5***8").to_s
puts "result should be 12, and is: "+add("//[***][ee]\n-3***4ee-5***8").to_s