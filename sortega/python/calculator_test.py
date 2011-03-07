from calculator import add

class TestCalculator:

    def test_empty(self):
        "addition of empty o null inputs is 0"
        assert 0 == add(None)
        assert 0 == add("")
        
    def test_simple(self):
        "addition of comma-separated integers"
        assert 2 == add("1,1")
        assert 7 == add("1,4,2")

    def test_multiple_numbers(self):
        assert 5050 == add("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16," +
                           "17,18,19,20,21,22,23,24,25,26,27,28,29," +
                           "30,31,32,33,34,35,36,37,38,39,40,41,42," +
                           "43,44,45,46,47,48,49,50,51,52,53,54,55," +
                           "56,57,58,59,60,61,62,63,64,65,66,67,68," +
                           "69,70,71,72,73,74,75,76,77,78,79,80,81," +
                           "82,83,84,85,86,87,88,89,90,91,92,93,94," +
                           "95,96,97,98,99,100")

    def test_newlines(self):
        assert 6 == add("1\n2,3")

    def test_custom_delimiter(self):
        assert 6 == add("//;\n1\n2;3")

    def test_reject_negatives(self):
        try:
            add("1,3,-1,4")
            assert False, "ValueError expected"
        except ValueError:
            pass

    def test_ignore_greater_than_1000(self):
        assert 2 == add("1001,2")
        assert 1002 == add("1000,2")

    def test_multicharacter_delimiter(self):
        assert 9 == add("//[***]\n1\n2***3***3")

    def test_multicharacter_multiple_delimiter(self):
        assert 9 == add("//[***][%]\n1\n2***3%3")
        assert 9 == add("//[***][->]\n1\n2***3->3")
