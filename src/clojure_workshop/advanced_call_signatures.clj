(ns clojure-workshop.advanced-call-signatures)

;;;; Destructuring Functions Parameters
;; Can use destructuring in function parameters
(def mapjet-booking
  {
   :id             8773
   :customer-name  "Alice Smith"
   :catering-notes "Vegetarian on Sundays"
   :flights        [
                    {
                     :from {:lat 48.9615 :lon 2.4372 :name "Paris Le Bourget Airport"},
                     :to   {:lat 37.742 :lon -25.6976 :name "Pnta Delgada Airport"}
                     },
                    {
                     :from {:lat 37.742 :lon -25.6976 :name "Ponta Delgada Airport"},
                     :to   {:lat 48.9615 :lon 2.4372 :name "Paris Le Bourget Airport"}
                     }
                    ]
   })

;; destructuring in function parameters
(defn print-flight
  [[[lat1 lon1] [lat2 lon2]]]
  (println "Flying from: Latitude:  " lat1 " Longitude: " lon1 " Flying to: Latitude " lat2 " Longitude " lon2))

(print-flight [[48.9615, 2.4372] [37.742 -25.6976]])

;; associative destructuring in function parameters
(defn print-mapjet-flight
  [{{lat1 :lat lon1 :lon} :from, {lat2 :lat lon2 :lon} :to}]
  (println "Flying from: Latitude:  " lat1 " Longitude: " lon1 " Flying to: Latitude " lat2 " Longitude " lon2))

(print-mapjet-flight {
                      :from {:lat 48.9615 :lon 2.4372 :name "Paris Le Bourget Airport"},
                      :to   {:lat 37.742 :lon -25.6976 :name "Pnta Delgada Airport"}
                      })

;;;; Arity overloading
(defn no-overloading []
  (println "Same old, same old..."))

(defn overloading
  ([] "No argument")
  ([a] (str "One argument: " a))
  ([a b] (str "Two arguments: " a " and " b)))