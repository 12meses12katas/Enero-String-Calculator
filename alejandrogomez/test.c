#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "string_calculator.h"

int main()
{
    printf ("\t*** TEST - STRING CALCULATOR ***\n");
    
    printf ("add(\"\") == 0 :\t\t\t\t\t\t");
    if (test_empty_string())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    printf ("add(\"1,2,3\") == 6 :\t\t\t\t\t");
    if (test_well_formed_string())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    printf ("add(\"1\\n2,3\") == 6 :\t\t\t\t\t");
    if (test_well_formed_string_cr())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    printf ("add(\"1\\n,2,3\") is bad input :\t\t\t\t");
    if (test_bad_formed_string())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    printf ("add(\"//[*][:]\\n1*2:3\") == 6 :\t\t\t\t");
    if (test_single_character_delimiter_definitions())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    printf ("add(\"//[***][:::1:::]\\n1***2:::1:::3\") == 6 :\t\t");
    if (test_multiple_character_delimiter_definitions())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    printf ("add(\"-1,2,-3\") is bad input (negatives not allowed) :\t");
    if (test_negative_numbers_not_allowed())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    printf ("add(\"1000,2,1001\") == 1002\t\t\t\t");
    if (test_ignore_values_greater_than_1000())
        printf ("+PASS+");
    else
        printf ("-FAIL-");
    printf("\n");
    
    return EXIT_SUCCESS;
}

int test_empty_string()
{
    return (add("") == 0);
}

int test_well_formed_string()
{
    return (add("1,2,3") == 6);
}

int test_well_formed_string_cr()
{
    return (add("1\n2,3") == 6);
}

int test_bad_formed_string()
{
    return (add("1\n,2,3") == ERROR_BAD_INPUT);
}

int test_single_character_delimiter_definitions()
{
    return (add("//[*][:]\n1*2:3") == 6);
}

int test_multiple_character_delimiter_definitions()
{
    return (add("//[***][:::1:::]\n1***2:::1:::3") == 6);
}

int test_negative_numbers_not_allowed()
{
    return (add("-1,2,-3") == ERROR_NEGATIVE_NUMBER);
}

int test_ignore_values_greater_than_1000()
{
    return (add("1000,2,1001") == 1002);
}