(ns genetic-math.utils)

(defn math-fn-to-clojure-fn
  [math-fn]
  "Turns something like this '(+ x 1) and return a function
  like `fn [x] (+ x 1)`"
  (fn [x]
    (def x x)
    (eval math-fn)))
