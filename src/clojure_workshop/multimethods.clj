(ns clojure-workshop.multimethods)

(defmulti strike
          (fn [m] (get m :weapon)))

