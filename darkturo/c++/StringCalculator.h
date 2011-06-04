#include <string>
#include <cassert>
#include <cstdlib>
#include <vector>
#include <algorithm>
#include <exception>
#include <bits/stl_numeric.h>

using namespace std;
class StringCalculatorException : exception {
    private:
        string msg;
    public:
    StringCalculatorException(string what) { msg = what; }
    ~StringCalculatorException() throw () { msg = ""; }

    const char* what() const throw() {
        return msg.c_str();
    }
};

class StringCalculator {
    public:
        int add(string theString) {
            unsigned int sum;

            const vector<int> * numbersList = extractNumbers(theString);
            sum = accumulate(numbersList->begin(), numbersList->end(), 0);

            delete numbersList;
            return sum;
        }
    private:
        vector<int> * extractNumbers(string theString) {
            vector<string> * numList = splitWithSeparator(",", theString);
            return convertToNumbers(numList);
        }

        vector<string> * splitWithSeparator(string sep, string theString) {
            vector<string> * strList = new vector<string>(0);
            string::size_type pos(0), tpos(0); 

            do {
                tpos = theString.find(sep, pos); 
                if (tpos != string::npos) {
                    strList->push_back(string(theString, pos, tpos - pos));
                    pos = tpos + sep.size();
                } else {
                    strList->push_back(string(theString, pos));
                }
            } while( tpos != string::npos );

            return strList;
        }

        vector<int> * convertToNumbers(vector<string> * theNumList) {
            vector <int> * numbersList = new vector<int>(0);
            for (vector<string>::iterator it = theNumList->begin(); it != theNumList->end(); it++) {
                char * endptr;
                int num = strtoll((*it).c_str(), &endptr, 10);
                if (*endptr == '\0') {
                    numbersList->push_back(num);
                } else {
                    throw StringCalculatorException("invalid input");
                }
            }
            return numbersList;
        }
};

