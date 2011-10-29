(ns stringcalculator.core
  (:use [clojure.string :exclude [replace reverse]])
  (:import [java.util.regex Pattern]))

(defn parseNumbers [regex input]
  (map read-string (remove blank? (split input regex))))

(defn delimiterRegex [delimiters]
  (let [quotedDelimiters (map #(Pattern/quote %) delimiters)]
    (re-pattern (reduce #(str %1 \| %2) "\\n" quotedDelimiters))))

(defn parseDelimiters [input]
  (if (blank? input) 
    nil
    (remove blank? (split input #"(\[|\])+"))))

(defn add [input]
  (let [match (re-matches #"(?://(.+?)\n)?((?:.|\n)*)" input)
        custom-delimiters (parseDelimiters (nth match 1))
        delimiters (if (seq custom-delimiters) custom-delimiters [","])
        numbers (parseNumbers (delimiterRegex delimiters) (nth match 2))]
    (if (seq (filter #(< % 0) numbers))
      (throw (new IllegalArgumentException "Negative number")))
    (reduce + (filter #(<= % 1000) numbers))))

