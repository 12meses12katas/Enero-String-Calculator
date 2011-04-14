#include <stdio.h>
#include <string.h>

int Add(char *numbers);

int main() {

	int result = Add("1;;;2;3");

	if(result == -1) {
		printf("Error: No se puede realizar la operación\n");
		printf("Compruebe que la sentencia sea válida y que los números sean positivos\n");
 	}	
	else {
		printf("result: %d\n", result);
	}
}

int Add(char *numbers) {
	
	size_t len = strlen(numbers);
	
	if(len == 0) {
		return 0;
	} else {
		if(len == 1) {
			int x = numbers[0] - '0'; //cambierte un char en int
			return x;
		} else if(len >= 3 && isdigit(numbers[0])) {
			int x = numbers[0] - '0';
			int i;
			for(i = 1; i < len; i += 2) {
				if(strrchr("\n;", numbers[i]) && isdigit(numbers[i+1])) {
					x += numbers[i+1] - '0';
				} else
					return -1;
			}
			return x;
		} else if(len >= 4 && strrchr("/", numbers[0]) && strrchr("/", numbers[1]) && strrchr("\n", numbers[3])) {
			char separador = numbers[2];
			int x = numbers[4] - '0';
			int i;
			for(i = 5; i < len; i += 2) {
				if(separador == numbers[i] && isdigit(numbers[i+1])) {
					x += numbers[i+1] - '0';
				} else
					return -1;
			}
			return x;
	
		} else {	
			return -1;
		}
	}
}
