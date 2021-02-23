(ns clojure-workshop.coll-playground)

;; Some useful function on the sequence abstraction
(def language {:name "Clojure" :creator "Rich Hickey" :platforms ["Java" "JavaScript" ".NET"]})

;; Use count to get the number of elements in a collection
(count language)

;; We can whether a collection is empty
(empty? language)
(empty? [])

;; A map is not sequential because there is no logical order between its elements
;; We can convert a map to a sequence using the seq function
(first (seq language))
(rest (seq language))
(nth (seq language) 1)

(first #{:a :b :c})
(rest #{:a :b :c})
(last language)

;; into puts elements of one collection into another collection
;; the first element is the target collection
(into [1 2 3 4] #{5 6 7 8})
(into #{} [1 2 3 3 3 4 5])

;; need vector of key value pairs to put into a map
(into {} [[:a 1] [:b 2]])

(into '() [1 2 3 4])                                        ;; => (4 3 2 1)

;; if you want to concatenate collection
(concat '(1 2) '(3 4))                                      ;; => (1 2 3 4)
(into '(1 2) '(3 4))                                        ;; => (4 3 1 2)

;; sort a list
(sort (into '(1 2) '(3 4)))