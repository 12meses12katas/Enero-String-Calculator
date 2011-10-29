(ns stringcalculator.test.core
  (:use [stringcalculator.core])
  (:use [clojure.test]))

(deftest empty-input
  (is (= 0 (add ""))))

(deftest single-op
  (testing "single operator"
    (is (= 1 (add "1")))
    (is (= 2 (add "2"))))
  (testing "comma-separated operators"
    (is (= 3 (add "1,2")))
    (is (= 10 (add "3,5,2")))))

(deftest delimiters
  (testing "newline"
    (is (= 10 (add "3\n5,2"))))
  (testing "with custom-delimiter line"
    (is (= 3 (add "//;\n1;2")))
    (is (= 3 (add "//.\n1.2"))))
  (testing "multicharacter"
    (is (= 10 (add "//[***]\n3\n5***2"))))
  (testing "multidelimiter"
    (is (= 11 (add "//;[***][->]\n3;5***2->1"))))
)

(deftest exception-on-negatives
  (is (thrown? IllegalArgumentException (add "1,-1"))))

(deftest ignore-greater-than-1000
  (is (= 2 (add "2,1001")))
  (is (= 1002 (add "2,1000"))))

