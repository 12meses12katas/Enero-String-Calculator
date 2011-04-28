#!/usr/bin/env python


def add(numbers):
    if not numbers:
        return 0
    numbers = numbers.replace("\n", ",")
    return sum(map(int, numbers.split(',')))

if __name__ == '__main__':
    assert add("") == 0
    assert add("1") == 1
    assert add("1,2") == 3
    assert add("1,2,3") == 6
    assert add("1\n2,3") == 6
