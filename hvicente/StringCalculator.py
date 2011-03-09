# Calculadora con entrada de string

# Programa - StringCalculator
# Escrito por - Helios Vicente Beltran

class StringCalculator:
	def add(self,numbers=None):
		if numbers is None or len(numbers)==0: 
			return 0
		else:
			numberList = numbers.split(',')
			sum = 0
			for num in numberList:
				sum += int(num)
			return sum
