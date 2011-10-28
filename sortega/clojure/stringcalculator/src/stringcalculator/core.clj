(ns stringcalculator.core
  (:use [clojure.string :exclude [replace reverse]]))

(defn parseNumbers [regex input]
  (map read-string (remove blank? (split input regex))))

(defn delimiterRegex [delimiter]
  (re-pattern (str delimiter "|\n")))

(defn add [input]
  (let [match (re-matches #"(?://(.)\n)?((?:.|\n)*)" input)
        custom-delimiter (nth match 1)
        delimiter (if (= nil custom-delimiter) "," custom-delimiter)
        numbers (parseNumbers (delimiterRegex delimiter) (nth match 2))]
    (if (seq (filter #(< % 0) numbers))
      (throw (new IllegalArgumentException "Negative number")))
    (reduce + numbers)))

