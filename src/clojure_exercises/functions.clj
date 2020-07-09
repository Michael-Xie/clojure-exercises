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