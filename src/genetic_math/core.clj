(ns genetic-math.core
  (:require [clojure.math.numeric-tower :as math]))

;;;; My goal is to try to start on the example you gave of trying to fit a function to points with genetic programming
;;;; It will be messy and horribly written, but I would like to give it a go.
;;;; When I remember, I will try to follow https://github.com/bbatsov/clojure-style-guide

;;; Create random function

;; 1. start with blank, then choose a random function.
;; 2. then choose two random arguments, either data or functions.
;; 3. if it is a function, then start back at 2, keeping track of recursion
(defn choose-random
  [v]
  "Return a random element of this vector"
  (nth v (rand (math/floor (count v)))))

(defn random-args [data functions recursion-levels-left]
  (dec recursion-levels-left)
  (if recursion-levels-left
    (do
      (repeatedly 2 #(choose-random functions data))))
    (do ; else
      (def args (repeatedly 2 #(choose-random (concat functions data))))
      (map (fn [arg]
               (if (contains? data arg) ; if the argument is just data (in data)
                 arg ; then return that,
                 ;; else it is a function so return that function plus two new arguments for it
                 (into ; http://stackoverflow.com/a/4095819/907060
                  ['arg]
                  (random-args data functions recursion-levels-left)))) ; get two new args for this new function
               args))) ; apply this get-arg-or-create-new-function over args

(defn random-function [data functions max-recursion-level]
  (def function (choose-random functions))
  (def args (random-args data functions max-recursion-level))
  (into [function] args))

;; Use vectors [] instead of list '() because made for when accesing
;; based on index and not appending or prepending
(random-function '[.1 x] '[+ - / *] 1)
