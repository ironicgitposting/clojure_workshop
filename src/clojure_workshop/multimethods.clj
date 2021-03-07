(ns clojure-workshop.multimethods)

;; runtime polymorphism
;; A multimethod is a combination of a dispatch method function
;; and one or more methods

;; the two main operators for creating those multimethods are defmulti and defmethod

;; defmulti declares a multimethod and define how the method is chosen is chosen with the dispatch function
;; defmethod creates the different implementations that will be chosen by the dispatch function

;; Create a multimethod called strike.
;; The second argument is the dispatch function, which simply retrives a weapon in a map
;; passed as a parameter
(defmulti strike (fn [m] (get m :weapon)))
;; or shorter
(defmulti strike :weapon)

;; Unmap a var from a namespace
(ns-unmap 'user 'strike)
(defmulti strike (fn [m] (:weapon m)))   ;; get the value from the :weapon key within the given map as arg

;; now that we have our multimethod and our dispatch function defined (which simply the :weapon keyword)
;; Lets create our strike functions for a couple of weapons to demonstrate the usage of defmethod
(defmethod strike :sword
  [game-map]
  (let [target (:target game-map)
        health (:health target)]
    (- health 100)))

(defmethod strike :cast-iron-saucepan
  [game-map]
  (let [target (:target game-map)
        health (:health target)]
    (- health 100 (rand-int 50))))

;; Calling strike with different arguments let us invoke two different functions.
(strike {:weapon :sword :target {:health 200}})
(strike {:weapon :cast-iron-saucepan :target {:health 200}})

;; When the dispatch value doesn't map to any registered function, an exception is thrown
(strike {:weapon :spoon :target {:health 200}})

;; We can add a :default dispatch value
(defmethod strike :default
  [{{:keys [:health]} :target}]
  health)                            ;; better destructuring than previous functions

(strike {:weapon :spoon :target {:health 200}})             ;; will return 200, :default dispatch function used

;; To summarize
;; 1 Define a multimethod and a dispatch function
;; the dispatch function is like a switch case, or pattern matching, here we get the :weapon value from a map

;; 2 Create an implementation per expected values (:weapon value)
