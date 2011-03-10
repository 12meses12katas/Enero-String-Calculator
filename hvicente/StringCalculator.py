# Calculadora con entrada de string

# Programa - StringCalculator
# Escrito por - Helios Vicente Beltran

class StringCalculator:
        def add(self,numbers=None):
                if numbers is None or len(numbers)==0:
                        return 0
                else:
                        delimitador = ','
                        numberList = ""
                        if numbers.find('//') != -1:
                            # Guardar delimitador nuevo
                            auxNumList = numbers.split('\n')
                            delimitador = auxNumList[0].replace('//','')
                            i=0
                            for num in auxNumList:
                                if(i!=0):
                                    numberList += num
                                i=i+1
                            numberList = numberList.split(delimitador)
                        else:
                            numberList = numbers.split(delimitador)
                        sum = 0
                        for num in numberList:
                                sum += int(num)
                        return sum
