#!/usr/bin/env python
# -*- encodign: utf-8 -+-


class StringCalculator:

    def add(self, nums):
        if nums == "":
            return 0

        # default delimiter it's ","
        delimiter = ","

        delimiter_found = nums.startswith("//")
        if delimiter_found:
            delimiter = nums.split("//")[1].split("\n")[0]

            if not delimiter:
                raise ValueError
            elif len(delimiter) > 1:
                if delimiter[0] != "[" or delimiter[-1] != "]":
                    raise ValueError
                else:
                    delimiter = delimiter[1:-1]

            nums = nums.split("\n", 1)[1]

        total = []
        lines = nums.split("\n")
        for line in lines:
            total += [int(x) for x in line.split(delimiter) if int(x) <= 1000]

        invalids = []
        for n in total:
            if n < 0:
                invalids.append(n)

        if invalids:
            raise ValueError, "negatives not allowed. Negatives found: " + ",".join([str(x) for x in invalids])

        result = sum(total)

        return result
