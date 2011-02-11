import re

DELIMITER_RE = r'^\\(.*)'

def add(input_string=""):
    if not input_string or len(input_string) == 0:
        return 0
    
    delimiter = ","
    (first_line, _a, number_list) = input_string.partition("\n")
    if len(number_list) == 0:
        number_list = first_line
    else:
        result = re.match(DELIMITER_RE, first_line)
        if result:
            delimiter = result.group(1)
        else:
            # No delimiter set
            number_list = first_line + "\n" + number_list


    numbers = []
    for line in number_list.split("\n"):
        numbers += line.split(delimiter)
    result = 0
    for number in numbers:
        result += int(number)

    return result

