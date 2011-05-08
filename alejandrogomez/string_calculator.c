#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "string_calculator.h"

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
    return 	s[0] == '/' && 
			s[1] == '/' && 
			belongs('\n', s);
}

int belongs(char c, string s)
{   
    return 	!empty_string(s) && 
			(s[0] == c || belongs(c, &s[1]));
}

int valid_input(string s)
{
    int i, s_length = length(s);
    int separator_found = 0;
    
    for(i = 0; i < s_length; i++)
    {
        if (is_separator(s[i]) && separator_found)
            return 0;
        else 
			separator_found = is_separator(s[i]);
    }
        
    return 1;
}

int is_separator(char c)
{
    return 	c == '.' ||
            c == ',' ||
            c == '\n';
}

string * take(string s, int n, string * res)
{
    int i, s_length = length(s);
    string aux = strdup(s);
    
    if (n > s_length)
        n = s_length;
    
    for(i = 0; i < n; i++)
        aux[i] = s[i];

    aux[i] = (long int) NULL;
	*res = aux;
	
	return res;
}

string * drop(string s, int n, string * res)
{
    int i, s_length = length(s);

    if (n > s_length)
        *res = NULL;
    else
    {
        string aux = strdup(s);
        take(&aux[n], s_length - n, res);
    }

	return res;
}

void extract_delimiter(string * s, string * delim)
{
    string raw = *s;
    int i, s_length = length(raw);
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
	else
	{
		*delim = "";
		return;
	}
            
    i = index_of(']', aux);
    if (i == -1)
        *delim = "";
    else
    {   
        take(aux, i, delim);
        drop(aux, i + 1, &clean);
        *s = clean;
    }
}

int index_of(char c, string s)
{
    int i, s_length = length(s);
    
    for(i = 0; i < s_length; i++)
    {
        if(s[i] == c)
            return i;
    }
    
    return -1;
}

string * replace_substring(string * s, string sub, char c)
{
    int sub_index, sub_length = length(sub);
    string raw = *s;
    string left, right;
    
    left = strdup(*s);
    right = strdup(*s);
    sub_index = is_substring(sub, *s);
    while (sub_index != -1)
    {
        take(raw, sub_index, &left);
        drop(&raw[sub_index], sub_length - 1, &right);
        right[0] = c;
        strcat(left, right);
        sub_index = is_substring(sub, left);
    }
    
    *s = left;
	
	return s;
}

int is_substring(string sub, string s)
{
    int i, max_index, sub_length;
    
    max_index = 0;
    if (!empty_string(sub))
    {
        sub_length = length(sub);
        max_index = length(s) - sub_length;
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
    return (!strcmp(a, b));
}

int to_number(string number)
{
    return ((int) strtol(number, (char **) NULL, 10));
}

int empty_string(string s)
{
    return (strlen(s) == 0);
}

int length(string s)
{
	return ((int) strlen(s));
}