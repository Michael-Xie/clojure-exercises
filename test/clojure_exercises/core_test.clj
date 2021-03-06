(ns clojure-exercises.core-test
  (:require [clojure.test :refer :all]
            [clojure-exercises.core :refer :all]
            [clojure-exercises.functions :refer :all]))

;; (deftest a-test
;;   (testing "FIXME, I fail."
;;     (is (= 0 1))))

(deftest functions-test
  (testing "sum"
    (is (= (sum 1 2) 3))
    (is (= (sum -1 2) 1))
    (is (= (sum 0 0) 0)))
  (testing "process-value"
    (is (= (process-value 10) :small-number))
    (is (= (process-value 11) :pretty-big))
    (is (= (process-value -1) :negative))
    (is (= (process-value 2) :small-number))
    (is (= (process-value "string") :a-string))
    (is (= (process-value \C) :something-else)))
  (testing "triangle"
    (is (= true (triangle? 3 4 5)))
    (is (= false (triangle? 1 1 2))))
  (testing "classify-triangle"
    (is (= :equilateral (classify-triangle 1 1 1)))

    (is (= :invalid (classify-triangle 0 0 0)))

    (is (= :invalid (classify-triangle 1 1 2)))

    (is (= :isosceles (classify-triangle 4 4 5)))

    (is (= :scalene (classify-triangle 3 4 5))))
  (testing "clamp"
    (is (= 2 (clamp 2 1 4)))

    (is (= 1 (clamp 0 1 4)))

    (is (= 4 (clamp 5 1 4))))
  (testing "process-list"
    (is (= (process-list-uncleaned [1 2 3 4 5 6]) (process-list-cleaned [1 2 3 4 5 6])))
    (is (= (process-list-uncleaned [1 3 5]) (process-list-cleaned [1 2 3 4 5 6]))))
  (testing "process-number"
    (is (= (process-number-uncleaned 2) (process-number-cleaned 2))))

  (testing "largest"
    (is (= 3 (largest [1 2 -1 3 3 2 1 0]))))

  (testing "scrabble"
    (is (= 8 (score "hello")))

    (is (= 17 (score "question")))

    (is (= 35 (score "quizzed"))))

  (testing "count-if"
    (is (= 3 (count-if even? [1 2 3 4 5 6])))

    (is (= 1 (count-if keyword? ["foo" :bar 'baz])))

    (is (= 2
           (count-if (fn [x]
                       (= x (reverse x)))
                     [[1 2 1] [1 2 3 4] [1]]))))
  (testing "convert-space-age"
    (is (= 1 (convert-space-age 30 :earth :saturn)))

    (is (= 78 (convert-space-age 10 :mars :mercury))))
  (testing "get-and-set"
    (is (= [1 [2 3 4]] (get-and-set 0 2 [1 3 4])))

    (is (= ["foo"
            {:a "baz"
             :b "quux"}]
           (get-and-set :a
                        "baz"
                        {:a "foo"
                         :b "quux"}))))
  (testing "add-info"
    (is (= {:people {1 {:name "james"
                        :points 1
                        :stuff "beep boop"}
                     2 {:name "rafd"
                        :points 5}}}
           (add-info {:people {1 {:name "james"
                                  :points 1}
                               2 {:name "rafd"
                                  :points 5}}}
                     1
                     :stuff
                     "beep boop"))))
  (testing "update-info"
    (is (= {:people {1 {:name "james"
                        :points 1}
                     2 {:name "RAFD"
                        :points 8}}}
           (update-info {:people {1 {:name "james"
                                     :points 1}
                                  2 {:name "rafd"
                                     :points 5}}}
                        2
                        3)))))