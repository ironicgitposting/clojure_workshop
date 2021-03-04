(ns clojure-workshop.higher-order-playground
  (:require [clojure.string :as string]))

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

;; Partial functions
;; ([f] [f arg1] [f arg1 arg2] [f arg1 arg2 arg3] [f arg1 arg2 arg3 & more])
;; Takes a function f and fewer than the normal arguments to f, and
;; returns a fn that takes a variable number of additional args. When
;; called, the returned function calls f with args + additional args.

;; Simple examples
(def marketing-adder (partial + 5))
(marketing-adder 10 5)                                      ;; 20

(defn greeter
  []
  (partial str "Hello "))

((greeter) "Patrick" "!")                                       ;; "Hello Patrick!"

;; Composing functions
;; ([] [f] [f g] [f g & fs])
;; Takes a set of functions and returns a fn that is the composition
;; of those fns.  The returned fn takes a variable number of args,
;; applies the rightmost of fns to the args, the next
;; fn (right-to-left) to the result, etc.

;; To summarize: `comp` is a utility function that takes any number of functions as parameter and returns a new function
;; that calls those functions in order, passing the result of each to the other (right to left)
((comp inc *) 2 2)                                          ;; 2 * 2 and then + 1

(defn hexify "Convert byte sequence to hex string" [coll]
  (let [hex [\0 \1 \2 \3 \4 \5 \6 \7 \8 \9 \a \b \c \d \e \f]]
    (letfn [(hexify-byte [b]
              (let [v (bit-and b 0xFF)]
                [(hex (bit-shift-right v 4)) (hex (bit-and v 0x0F))]))]
      (apply str (mapcat hexify-byte coll)))))

(defn unhexify "Convert hex string to byte sequence" [s]
  (letfn [(unhexify-2 [c1 c2]
            (unchecked-byte
              (+ (bit-shift-left (Character/digit c1 16) 4)
                 (Character/digit c2 16))))]
    (map #(apply unhexify-2 %) (partition 2 s))))

(defn unhexify-str [s]
  (apply str (map char (unhexify s))))

(defn hexify-str [s]
  (hexify (.getBytes s)))

((comp clojure.string/upper-case unhexify-str hexify-str) "Patrick")