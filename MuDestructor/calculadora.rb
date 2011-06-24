
class Calculadora

  @@resultado = 0

  def reset()
    @@resultado = 0
  end

  def add(numbers)
    numbers.scan(/[0-9]+/) do |w|
      # puts "Nuevo valor a sumar " + w
      # puts "Valor sumado hasta ahora" + @@resultado.to_s()
      if Integer(w)<1000
        @@resultado = @@resultado + Integer(w)
      end
    end
    return @@resultado
  end

end

