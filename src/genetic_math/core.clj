(ns genetic-math.core
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.test :refer [function?]]))

;;;; My goal is to try to start on the example you gave of trying to fit a function to points with genetic programming
;;;; It will be messy and horribly written, but I would like to give it a go.
;;;; When I remember, I will try to follow https://github.com/bbatsov/clojure-style-guide

;;; Create random function

;; 1. start with blank, then choose a random function.
;; 2. then choose two random arguments, either data or functions.
;; 3. if it is a function, then start back at 2, keeping track of recursion

(declare random-function) ; so that you can reference it before assignment http://stackoverflow.com/a/18421533/907060

(defn random-function-or-data [possible-values recursion-levels-left]
  (if (zero? recursion-levels-left) ; if-else from http://stackoverflow.com/a/4943866/907060
    (rand-nth (remove function? possible-values)) ; if the recursions level is reaches return just some data
    (random-function possible-values (dec recursion-levels-left)))) ; else return a new function

(defn random-math-function [possible-values recursion-levels-left]
  (list (rand-nth (filter function? possible-values))
   (random-function-or-data possible-values recursion-levels-left)
   (random-function-or-data possible-values recursion-levels-left)))


;; Use vectors [] instead of list '() because made for when accesing
;; based on index and not appending or prepending
(random-math-function '[0.1 x + - / *] 1)


(defn random-lisp-function
  [possible-values recursion-levels-left]
  "gives you a random clojure math function"
  (fn [x] (eval (random-math-function possible-values recursion-levels-left))))



(random-lisp-function '[0.1 x + - / *] 1)
((random-lisp-function '[0.1 x + - / *] 1) 3)
