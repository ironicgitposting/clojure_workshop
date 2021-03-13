(ns clojure-workshop.map-seq-playground)

;; The first argument of map is always a function
(map inc [1 2 3])

;; Exercise 4.01
;1
(map (fn [i] (* i 10)) [1 2 3 4 5])

;2
(map count ["Let's" "measure" "word" "length" "now"])

;3
(map #(str % ": " (count %)) ["Let's" "measure" "word" "length" "now"])

;; Unlike map, filter can, and often does, produce a sequence of results containing fewer
;; items than the inpur sequence
(filter keyword? ["a" :b (keyword "c") :d "e" "f"])

;; Exercise 4.02
;1
(odd? 5)

;2
(odd? 6)

;3
(filter odd? [1 2 3 4 5])                                   ;; will return odd coll members

;4
(remove odd? [1 2 3 4 5])                                   ;; will return all even coll members

;5 The result of a filter operation is always a seq
; The constantly function returns a function that does nothing
; but return a single value, regardless of the number of arguments
(filter (constantly true) [1 2 3 4 5])                      ;; (1 2 3 4 5)
(filter (constantly false) [1 2 3 4 5])                     ;; ()

; take, take-while, drop drop-while
(take 3 [1 2 3 4 5])                                        ;; (1 2 3)
(drop 3 [1 2 3 4 5])                                        ;; (4 5)
(take-while #(>= 3 %) [1 2 3 4 5])                          ;; (1 2 3)

;; Exercise 4.03

;1
(def students [{:name "Eliza" :year 1994}
               {:name "Salma" :year 1995}
               {:name "Jodie" :year 1997}
               {:name "Kaitlyn" :year 2000}
               {:name "Alice" :year 2001}
               {:name "Pippa" :year 2002}
               {:name "Fleur" :year 2002}])

;2
(defn born-before?
  [n]
  #(< (:year %) n))

;3
(take-while (born-before? 1997) students)

;4
(map :name (take-while (born-before? 2001) students))

;; Using map and filter together
(map (fn [n] (* 10 n)) (filter odd? [1 2 3 4 5]))

;; Same expression with threading macros
;; works just like elixir's |>
(->> [1 2 3 4 5]
     (filter odd?)
     (map (fn [n] (* 10 n))))

;; threading allows us to preserve the logical order of execution
;; without having to name the returns values

;; comp vs ->>
;* `comp` works with functions, and `->>` works with forms.

;* `comp` is runtime, while `->>` is compile-time. The arguments to `comp` will
;be evaluated at runtime and should be callable functions.

;; Thanks to the threading macro, a complex transformation can be broken down into smaller,
;; composable steps, which are easier to write, test, and understand