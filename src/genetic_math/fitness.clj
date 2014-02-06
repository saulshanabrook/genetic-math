(ns genetic-math.fitness
  (:require [clojure.math.numeric-tower :as math])
  (:require [genetic-math.utils :refer [math-fn-to-clojure-fn]]))


(defn function-fitness
  [data math-fn]
  "Computers sum of absolute error of the data from the math function
  Expects the data to be in a vector of vectors, [[0 .5] [1 44]] as x y pairs
  Exepcts math-function to be something that can be evaluated with x to return
  a y, like (+ x (* 0.1 x))"
  (reduce
   +
   (for [[x y] data]
     (math/abs
      (- y ((math-fn-to-clojure-fn math-fn) x))))))
