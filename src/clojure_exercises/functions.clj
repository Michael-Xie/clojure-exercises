(ns clojure-exercises.functions)

(defn sum
  [a b]
  (+ a b))

;; cond
(defn process-value
  [value]
  (cond
    (string? value) :a-string
    (not (number? value)) :something-else
    (> value 10) :pretty-big
    (< value 0) :negative
    :else :small-number))

(defn triangle?
  [a b c]
  (and (> (+ a b) c)
       (> (+ b c) a)
       (> (+ a c) b)))

(defn classify-triangle
  [a b c]
  (cond
    (not (triangle? a b c)) :invalid
    (and (= a b) (= b c) (= a c)) :equilateral
    (or (= a b) (= a c) (= b c)) :isosceles
    :else :scalene))

(defn clamp
  [x min max]
  (cond
    (< x min) min
    (> x max) max
    :else x))

(defn process-number-uncleaned [n] (* (+ (inc (/ n 1.5)) 2) 10))

(defn process-number-cleaned [n] (-> n
                                     (/ 1.5)
                                     inc
                                     (+ 2)
                                     (* 10)))
(defn process-list-uncleaned
  [coll]
  (map (fn [x]
         (* x 10))
       (filter even? (map inc coll))))

(defn process-list-cleaned
  [coll] ; [1 2 3 4 5 6]
  (->> coll
       (map inc) ; [2 3 4 5 6 7] lazy sequence
       (filter even?) ; [2 4 6] lazy sequence
       (map (fn [x]
              (* x 10))) ; [20 40 60] lazy sequence
       ))

(defn largest [nums]
  (apply max nums))

(defn score [word]
  (def points
    {\a 1
     \b 3
     \c 3
     \d 2
     \e 1
     \f 4
     \g 2
     \h 4
     \i 1
     \j 8
     \k 5
     \l 1
     \m 3
     \n 1
     \o 1
     \p 3
     \q 10
     \r 1
     \s 1
     \t 1
     \u 1
     \v 4
     \w 4
     \x 8
     \y 4
     \z 10})
  (->> word
       (map points)
       (reduce +)))

;; Write a function count-if that counts the number of items in a collection that pass a certain predicate.

(defn count-if [pred coll]
  (->> coll
       (filter pred)
       (count)))

;; Implement a function that lets someone convert their age on one planet to their age on another.
;; For example:
;; 30 years on Earth is about 1 year on Saturn (a year being a single revolution around the sun).
;; 10 years on Mars is 78 years on Mercury.
;; You will need to look up the relevant data on planets yourself.

(def lookup
  {:mercury 0.2408467
   :venus 0.61519726
   :earth 1
   :mars 1.8808158
   :jupiter 11.862615
   :saturn 29.447498
   :uranus 84.016846
   :neptune 164.79132})
(defn convert-space-age [age planet1 planet2]
  (int (* (/ (lookup planet1) (lookup planet2)) age)))


    ;; (is (= 1 (convert-space-age 30 :earth :saturn)))

    ;; (is (= 78 (convert-space-age 10 :mars :mercury)))))