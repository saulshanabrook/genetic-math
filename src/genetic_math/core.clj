(ns genetic-math.core
  (:require [genetic-math.function :refer [random-math-function]])
  (:require [genetic-math.fitness :refer [function-fitness]])
  (:require [genetic-math.filters :refer [reproduction mutation]])
  (:require [roul.random :as rr]))


;; Top down approach

#_(def find-function (create-find-function [options]))
#_(def function (find-function [input-output]))

(defn symbolic-regression [training-data]
  (def max-depth 3)
  (def symbols `(+ - / * x 0.1))
  (def init-pop-count 4)
  (def rounds 4)
  (def replication-number 1)
  (def reproduction-number 2)
  (def mutation-number 1)

  (let [generate-function #(random-math-function symbols max-depth)]
    (def init-functions (repeatedly init-pop-count generate-function)))
  (loop [functions init-functions current-round 1]
    (if (> current-round rounds)
      (let [function-to-fitness (for [function functions] [:function (function-fitness training-data function)])
            select-function #(rr/rand-nth-weighted functions function-to-fitness)]
        (recur
         (concat
          (repeatedly replication-number select-function) ; replication
          (map (repeatedly replication-number #(reproduction (repeatedly 2 select-function)))) ; reproduction
          (map (map mutation (repeatedly mutation-number select-function)))) ; mutation
         (inc current-round))))))
