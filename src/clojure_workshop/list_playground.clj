(ns clojure-workshop.list-playground)

;; List are sequential collection, similar to vectors, but items are added to the front
;; used for LIFO structures

'(1 2 3)
(list :a :b :c)
(first (list :a :b :c))
(rest (list :a :b :c))

;; Cannot use the get function with a list
;; Can use nth but not very efficient
(nth (list :a :b :c) 1)                                     ;; :b

(def my-todo (list "Feed the cat" "Clean the bathroom"  "Save the world"))

;; Add items to a list
(cons "Go to work" my-todo)                                 ;; added to the front of the list
(conj my-todo "Go the work" "Wash my socks")                ;; Added to the front LIFO
;; => ("Wash my socks" "Go the work" "Feed the cat" "Clean the bathroom" "Save the world")
