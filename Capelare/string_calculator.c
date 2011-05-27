#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "string_calculator.h"

int add(string numbers){
	string tks;
	int result = 0;
	
	string nums = strdup(numbers);
	tks = strtok(nums, ",");
	while(tks != NULL){
		result+=atoi(tks);
		tks = strtok(NULL, ",");
	}
	
	return result;
}