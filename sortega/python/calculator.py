"""Calculator code kata"""

import re

def add(input_text):
    """Adds comma-separated numbers."""
    if input_text == None:
        return 0

    lines = input_text.splitlines()

    # custom delimiter?
    if lines and lines[0].startswith('//'):
        delimiter_spec = lines.pop(0)[2:]
        delimiters = [multi or simple 
            for (multi, simple)
            in re.findall(r'\[(.*?)\]|(.)', delimiter_spec)]
    else:
        delimiters = [',']

    del_regex = '|'.join([re.escape(delimiter) for delimiter in delimiters])
    tokens = sum([re.split(del_regex, line) for line in lines], [])
    numbers = [int(token) for token in tokens]
    if [n for n in numbers if n < 0]:
        raise ValueError("Negatives not allowed")

    return sum([n for n in numbers if n <= 1000], 0)
