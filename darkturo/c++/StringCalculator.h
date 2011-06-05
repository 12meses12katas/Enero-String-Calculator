#include <string>
#include <cassert>
#include <cstdlib>
#include <vector>
#include <sstream>
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
    private:
        string str;

    public:
        int add(string theString) {
            unsigned int sum;
            str =  theString;

            const vector<int> * numbersList = extractNumbers();
            sum = accumulate(numbersList->begin(), numbersList->end(), 0);

            return sum;
        }

    private:
        vector<int> * extractNumbers() {
            vector<string> * numList = splitStringWith(getDelimiters());
            return convertToNumbers(numList);
        }

        vector<string> * getDelimiters() {
            vector<string> * delimiters = new vector<string>(0);
            delimiters->push_back(",");
            delimiters->push_back("\n");
            extractDelimitersFromString(delimiters);
            return delimiters;
        }

        void extractDelimitersFromString(vector<string> * delimiters) {
            if (str.find_first_of("//") == 0) {
                string::size_type endOfSequence = str.find_first_of("\n");
                parseNewDelimiters(string(str, 2, endOfSequence - 2), delimiters);
                str.erase(0, endOfSequence);
            }
        }

        void parseNewDelimiters(string analizedString, 
                                vector<string> * delimiters) 
        {
            delimiters->push_back(analizedString);
        }

        vector<string> * splitStringWith(vector<string> * delimiters) {
            vector<string> * strList = new vector<string>(0);
            string::size_type pos(0), tpos(0); 
            string sep;

            do {
                tpos = findInString(delimiters, pos, sep);
                if (tpos != string::npos) {
                    strList->push_back(string(str, pos, tpos - pos));
                    pos = tpos + sep.size();
                } else {
                    strList->push_back(string(str, pos));
                }
            } while( tpos != string::npos );

            return strList;
        }

        string::size_type findInString(vector<string> *delimiters, 
                                                string::size_type pos, 
                                                string & sep) 
        {
            MatchWithDelimiter result = for_each(delimiters->begin(), 
                                                 delimiters->end(), 
                                                 MatchWithDelimiter(str, pos));
           
            sep = result.delimiter; 
            return result.position;
        }

        vector<int> * convertToNumbers(vector<string> * theNumList) {
            ToInt result = for_each(theNumList->begin(), 
                                    theNumList->end(), 
                                    ToInt());

            if (result.negatives->size() > 0) {
                string msg("negatives not allowed: ");
                msg.append(result.negativesToString());
                throw StringCalculatorException(msg);
            }
            return result.numbersList;
        }

        // * * * Functors * * * //
        class MatchWithDelimiter: public unary_function<string, void> {
            private:
                string analizedString;
                string::size_type pos;
                bool firstIteration;
            public:
                string::size_type position;
                string delimiter; 

                MatchWithDelimiter(string theString, string::size_type thePos) : 
                                   analizedString(theString), pos(thePos), 
                                   firstIteration(true) {}

                void operator() (string sep) { 
                    string::size_type founded = analizedString.find(sep, pos);
                    if (founded < position or firstIteration) {
                        position = founded; 
                        delimiter= sep;
                        firstIteration = false;
                    }
                }
        };

        class ToInt : public unary_function<string, void> {
            private:
                bool foundNegative;
                stringstream * ss;
            public:
                vector <int> * numbersList; 
                vector <int> * negatives;

                ToInt() {
                    numbersList = new vector<int>(0);
                    negatives   = new vector<int>(0);
                    foundNegative = false;
                    ss = new stringstream();
                }

                void operator() (string x) { 
                    char * endptr;
                    int num = strtoll(x.c_str(), &endptr, 10);
                    if (*endptr == '\0') {
                        if (num >= 0) { 
                            numbersList->push_back(num);
                        } else {
                            negatives->push_back(num);
                            if (foundNegative) *ss << ", ";
                            else foundNegative = true;
                            *ss << num;
                        }
                    } else {
                        throw StringCalculatorException("invalid input");
                    }
                }

                string negativesToString() {
                    return ss->str();
                }
        };
};

