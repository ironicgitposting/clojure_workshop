(ns clojure-workshop.vector-playground)

;; You can think of vectors as immutable arrays
;; Use a vector when you need to use or read elements in orders
[1 2 3]
(vector 1 2 3 4 5 "Six")

;; You can create a vector from another collection
(vec #{1 2 3})

;; Look up values in vectors with their index
(get [:a :b :c] 0)                                          ;; :a
(get [:a :b :c] 10)                                         ;; nil

(def fibonacci [0 1 1 2 3 5 8])
(get fibonacci 6)                                           ;; 8

;; As with map in sets, you can use the vector as a function to look up items
(fibonacci 6)                                               ;; 8

;; Add values to a vector
(conj fibonacci 13 21)                                      ;; to the end of the vector
(cons nil fibonacci)                                        ;; to the start of the vector