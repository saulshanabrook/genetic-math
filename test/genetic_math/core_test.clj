(ns genetic-math.core-test
  (:require [midje.sweet :refer :all])
  (:require [genetic-math.core :refer :all]))


(facts "about `choose-random`"
  (fact "it returns an item of the vector"
    (choose-random [1 2 3]) => (partial contains #{1 2 3}))) ; http://stackoverflow.com/questions/3249334/test-whether-a-list-contains-a-specific-value-in-clojure#comment3364505_3249401
