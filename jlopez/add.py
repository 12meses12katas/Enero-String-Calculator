import re


INIT_DELIMITER = r'^//'
DELIMITER_RE = r'\[?([^]]+)\]?'


def add(input_string=""):
    if not input_string or len(input_string) == 0:
        return 0
    
    delimiters = []
    (first_line, _a, number_list) = input_string.partition("\n")
    if len(number_list) == 0:
        number_list = first_line
        delimiters = [","]
    else:
        init_re = re.compile(INIT_DELIMITER)
        delimiter_re = re.compile(DELIMITER_RE)
        
        result = init_re.match(first_line)
        if result:
            delimiters_string = init_re.sub("",first_line)
            for delimiter in delimiter_re.findall(delimiters_string):
                delimiters.append(re.escape(delimiter))
        else:
            # No delimiter set
            delimiters = [","]
            number_list = first_line + "\n" + number_list

    delimiter_re = re.compile("|".join(delimiters))

    numbers = []
    for line in number_list.split("\n"):
        numbers += delimiter_re.split(line)
        
    result = 0
    for number in numbers:
        number = int(number)
        if number < 0:
            raise NegativeNumberException
        if number <= 1000:
            result += int(number)

    return result



class NegativeNumberException(Exception): "no negative"
