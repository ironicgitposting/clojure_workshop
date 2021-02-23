(ns clojure-workshop.sets-playground)

;; Create a set
#{1 2 3 4 5}
(hash-set :a :b :c :d)
(set [:a :b :b :c])
(sorted-set ["No" "Copy" "Cats" "Cats" "Please"])

;; Create a set
(def supported-currencies #{"Dollar" "Japanese yen" "Euro"})

;; Get an entry from a set
(get supported-currencies "Dollar")
(get supported-currencies "Swiss franc")

;; It's better to use contains to check for containment
(contains? supported-currencies "Dollar")

;; Add an entry to a set
(conj supported-currencies "BTC" "ETH")

;; Remove an entry from a set
(disj supported-currencies "Dollar")