#define ERROR_BAD_INPUT -1
#define ERROR_NEGATIVE_NUMBER -2

typedef char * string;

int add(string numbers);
int has_delimiter_definitions(string s);
int belongs(char c, string s);
int valid_input(string s);
int is_separator(char c);
string * take(string s, int n, string * res);
string * drop(string s, int n, string * res);
void extract_delimiter(string * s, string * delim);
int index_of(char c, string s);
string * replace_substring(string * s, string sub, char c);
int is_substring(string sub, string s);
int equal_strings(string a, string b);
int to_number(string number);
int empty_string(string s);
int length(string s);