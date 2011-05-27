#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#include "string_calculator.h"

#define RED 31

int main(){
	printf("\n");
	printf("******************************\n");
	printf("** TEST - STRING CALCULATOR **\n");
	printf("******************************\n\n");
	
	printf("add(\"\") == 0\n");
	if(add("")==0)
		printf("\t+PASS+\n");
	else
		printf("\t-FAIL-\n");
	
	printf("add(\"1,2,3\") == 6\n");
	if(add("1,2,3")==6)
		printf("\t+PASS+\n");
	else
		printf("\t-FAIL-\n");
}