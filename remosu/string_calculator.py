#!/usr/bin/env python


def add(numbers):
    if not numbers:
        return 0
    delimiter = ','
    if numbers.startswith('//'):
        delimiter, numbers = numbers.split('\n', 1)
        delimiter = delimiter.strip('/[]')
    numbers = numbers.replace("\n", delimiter)
    numbers = map(int, numbers.split(delimiter))
    negatives = [number for number in numbers if number < 0]
    if negatives:
        raise Exception('negatives not allowed', negatives)
    numbers = [number for number in numbers if number <= 1000]
    return sum(numbers)

if __name__ == '__main__':
    assert add("") == 0
    assert add("1") == 1
    assert add("1,2") == 3
    assert add("1,2,3") == 6
    assert add("1\n2,3") == 6
    assert add("//;\n1;2") == 3
    try:
        add("-1")
    except Exception as e:
        assert e.args[0] == 'negatives not allowed'
        assert e.args[1] == [-1] 
    assert add("2,1001") == 2
    assert add("//[***]\n1***2***3") == 6
