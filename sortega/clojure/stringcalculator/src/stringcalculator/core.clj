(ns stringcalculator.core
  (:use [clojure.string :exclude [replace reverse]])
  (:import [java.util.regex Pattern]))

(defn delimiterRegex [delimiters]
  (re-pattern 
    (join \| (map #(Pattern/quote %) delimiters))))

(defn parseNumbers [delimiters lines]
  (let [regex (delimiterRegex delimiters)]
    (map read-string 
         (mapcat #(split % regex) lines))))

(defn parseDelimiters [header]
  (re-seq #"[^\[\]]+" (.substring header 2)))

(defn add [input]
  (if (blank? input) 0
    (let [[header & body :as lines] (split-lines input)
          nums (if (.startsWith header "//")
                  (parseNumbers (parseDelimiters header) body)
                  (parseNumbers [","] lines))]
      (when (some neg? nums)
        (throw (IllegalArgumentException. "Negative number")))
      (reduce + (remove #(> % 1000) nums)))))
