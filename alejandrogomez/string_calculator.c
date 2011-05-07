#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ERROR_BAD_INPUT -1
#define ERROR_NEGATIVE_NUMBER -2

typedef char * string;

int add(string numbers);
int has_delimiter_definitions(string s);
int belongs(char c, string s);
int valid_input(string s);
int is_separator(char c);
void take(string s, int n, string * res);
void drop(string s, int n, string * res);
void extract_delimiter(string * s, string * delim);
int index_of(char c, string s);
void replace_substring(string * s, string sub, char c);
int is_substring(string sub, string s);
int equal_strings(string a, string b);
int to_number(string number);
int empty_string(string s);

int add(string numbers)
{
    string nums, separators = ".,\n";
    string * s;
    int negatives_found, result = 0;
    
    if (empty_string(numbers))
        return result;
    else if (has_delimiter_definitions(numbers))
    {
        // extract and replace delimiters
        // use 'nums' to store result
        nums = strdup(numbers);
        drop(numbers, 2, &nums);
        string delimiter = strdup(nums);
        extract_delimiter(&nums, &delimiter);
        while (!empty_string(delimiter))
        {
            replace_substring(&nums, delimiter, '.');
            extract_delimiter(&nums, &delimiter);
        }
    }
    else
        nums = strdup(numbers);     
    
    if (!valid_input(nums))
        return ERROR_BAD_INPUT;
        
    negatives_found = 0;
    string negatives = malloc(sizeof(nums));
    string nums_tok = strdup(nums);
    string num = strtok(nums_tok, separators);
    while (num != NULL)
    {
        int n = to_number(num);
        if (n < 0)
        {
            if (negatives_found)
                strcat(negatives, ",");
            else
                negatives_found = 1;
            strcat(negatives, num);
        }
        else if (n <= 1000)
            result += n;
        num = strtok(NULL, separators); 
    }
    
    free(nums_tok); 
    free(nums);
    
    if (negatives_found)
    {
        fprintf(stderr, "Negatives not allowed: %s\n", negatives);
        free(negatives);
        return ERROR_NEGATIVE_NUMBER;
    }
    
    return result;
}

int has_delimiter_definitions(string s)
{
    return (s[0] == '/') && (s[1] == '/') && belongs('\n', s);
}

int belongs(char c, string s)
{   
    if (empty_string(s))
        return 0;
    else
        return ( s[0] == c || belongs(c, &s[1]) );
}

int valid_input(string s)
{
    int i, s_length = (int) strlen(s);
    int separator_found = 0;
    
    for(i = 0; i < s_length; i++)
    {
        if (is_separator(s[i]) && separator_found)
            return 0;
        else if (is_separator(s[i]))
            separator_found = 1;
        else
            separator_found = 0;
    }
        
    return 1;
}

int is_separator(char c)
{
    return ( c == '.' ||
             c == ',' ||
             c == '\n' );
}

void take(string s, int n, string * res)
{
    int i, s_length = (int) strlen(s);
    string aux = strdup(s);
    
    if (n > s_length)
        n = s_length;
    
    for(i = 0; i < n; i++)
        aux[i] = s[i];
    aux[i] = (long int) NULL;
        
    strcpy(*res, aux);
    free(aux);
}

void drop(string s, int n, string * res)
{
    int i, s_length = (int) strlen(s);

    if (n > s_length)
        *res = NULL;
    else
    {
        string aux = strdup(s);
        take(&aux[n], s_length - n, res);
        free(aux);
    }
}

void extract_delimiter(string * s, string * delim)
{
    string raw = *s;
    int i, s_length = (int) strlen(raw);
    string clean = strdup(raw);
    string aux = strdup(raw);
    
    if (aux[0] == '[')
        drop(raw, 1, &aux);
    else if(aux[0] == '\n')
    {
        drop(raw, 1, &aux);
        *s = aux;
        *delim = "";
        return;
    }
            
    i = index_of(']', aux);
    
    if (i == -1)
        *delim = "";
    else
    {   
        // extract delim
        take(aux, i, delim);
        // clean s
        drop(aux, i + 1, &clean);
        *s = clean;
    }
}

int index_of(char c, string s)
{
    int i;
    
    for(i = 0; i < (int) strlen(s); i++)
    {
        if(s[i] == c)
            return i;
    }
    
    return -1;
}

void replace_substring(string * s, string sub, char c)
{
    int sub_index, sub_length = (int) strlen(sub);
    string raw = *s;
    string left, right;
    
    left = strdup(raw);
    right = strdup(raw);
    
    sub_index = is_substring(sub, raw);
    if (sub_index != -1)
    {
        // save left part
        take(raw, sub_index, &left);
        
        // delete delimiter and put c on its place
        drop(&raw[sub_index], sub_length - 1, &right);
        right[0] = c;
        
        // build the entire string and check for substring
        strcat(left, right);
        sub_index = is_substring(sub, left);
    }
    
    *s = left;
}

int is_substring(string sub, string s)
{
    int i, max_index, sub_length;
    
    max_index = 0;
    if (!empty_string(sub))
    {
        sub_length = (int) strlen(sub);
        max_index = (int) strlen(s) - sub_length;
    }
    
    for(i = 0; i <= max_index; i++)
    {
        string s_sub = malloc(sizeof(sub));
        take(&s[i], sub_length, &s_sub);
        if (equal_strings(sub, s_sub))
        {
            free(s_sub);
            return i;
        }
        free(s_sub);
    }
    
    return -1;
}

int equal_strings(string a, string b)
{
    return ( !strcmp(a, b) );
}

int to_number(string number)
{
    return ( (int) strtol(number, (char **) NULL, 10) );
}

int empty_string(string s)
{
    return ( strlen(s) == 0 );
}

/*********
 * Test. *
 *********/

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
    return ( add("") == 0 );
}

int test_well_formed_string()
{
    return ( add("1,2,3") == 6 );
}

int test_well_formed_string_cr()
{
    return ( add("1\n2,3") == 6 );
}

int test_bad_formed_string()
{
    return ( add("1\n,2,3") == ERROR_BAD_INPUT );
}

int test_single_character_delimiter_definitions()
{
    return ( add("//[*][:]\n1*2:3") == 6 );
}

int test_multiple_character_delimiter_definitions()
{
    return ( add("//[***][:::1:::]\n1***2:::1:::3") == 6 );
}

int test_negative_numbers_not_allowed()
{
    return ( add("-1,2,-3") == ERROR_NEGATIVE_NUMBER );
}

int test_ignore_values_greater_than_1000()
{
    return ( add("1000,2,1001") == 1002 );
}
