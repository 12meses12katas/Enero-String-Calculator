import string
import re
class StringCalculator():
    SEPARADOR_ESTANDAR = ","
    SEPARADOR_EXTRA = "\n" 
    def Add(self, numbers):
        resultado = 0
        if(numbers == ""): return resultado

        cadenaNormalizada = string.replace(numbers, self.SEPARADOR_EXTRA, self.SEPARADOR_ESTANDAR)
        for num in cadenaNormalizada.split(self.SEPARADOR_ESTANDAR):
            resultado += int(num)
        return resultado
