(ns genetic-math.core-test
  (:require [clojure.test :refer :all])
  (:require [genetic-math.utils :refer :all]))


(deftest math-fn-to-clojure-fn-test
  (testing "Return Type"
    (is (function? (math-fn-to-clojure-fn 'x))))
  (testing "Return Function Called"
    (is (=
         (
          (math-fn-to-clojure-fn '(+ x 1))
          3)
         4))))
