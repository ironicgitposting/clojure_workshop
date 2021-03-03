(ns clojure-workshop.higher-order-playground)

(update {:item "Tomato" :price 1.0} :price (fn [x] (/ x 2)))
(update {:item "Apple" :price 2.5} :price #(* % 3))

;; Invert the value of a boolean
(update {:item "Salad" :fruit true} :fruit not)

;; basic example of higher order function
(defn operate [f x] (f x))
(operate inc 2)
(operate clojure.string/upper-case "Hello.")

;; clojure.core/apply
;; ([f args] [f x args] [f x y args] [f x y z args] [f a b c d & args])
;; Applies fn f to the argument list formed by prepending intervening arguments to args.

;; Disassemble a sequence and apply a function to that sequence's elements
(+ [1 2 3])                                                 ;; error
(apply + [1 2 3])                                           ;; 6

(defn operate-2 [f & args]                                  ;; args will be a sequence of arguments
  (apply f args))

(operate-2 str "It " "Should " "Concatenate!")

;; You can also return functions
(defn higher-order-+
  []
  +)                                                        ;; Will return the + function

((higher-order-+) 2 2)                                      ;; 4


;; Use fn? to check if a returned value is a function
(fn? higher-order-+)                                        ;; true

;; Another example with everything tied together
(defn random-fn [] (first (shuffle [+ - * /])))

(defn mysterious-fn
  [f & args]
  (let [mysterious-fn (f)]
    (apply mysterious-fn args)))

(mysterious-fn random-fn 1 2 3 4 5)