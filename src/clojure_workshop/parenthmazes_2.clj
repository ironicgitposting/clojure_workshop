(ns clojure-workshop.parenthmazes-2)

;; 01
(def weapon-fn-map
  {:fist               (fn [health]
                         (if (< health 100)
                           (- health 10)
                           health))

   :staff              (partial + 35)
   :sword              #(- % 75)
   :cast-iron-saucepan #(- % 100 (rand-int 50))
   :sweet-potato identity
   })

;; 02
((:fist weapon-fn-map) 150)
((:fist weapon-fn-map) 50)

;; 03
((:staff weapon-fn-map) 100)

;; 04
;; 05
((:sword weapon-fn-map) 200)

;; 08
((:cast-iron-saucepan weapon-fn-map) 200)

;; (defn identity
;;  "Returns its argument."
;; {:added  "1.0"
;; :static true}
;; [x] x )
((:sweet-potato weapon-fn-map) 100)

;; view the code source of a function just like `doc`
(require '(clojure.repl :as repl))
(repl/doc rand-int)
(repl/source rand-int)
(repl/source identity)

