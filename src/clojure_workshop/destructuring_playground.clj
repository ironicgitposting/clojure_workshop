(ns clojure-workshop.destructuring-playground)

;; Basic example
(def coords [48.9615, 2.4372])

(defn print-coords
  [coords]
  (let [lat (first coords)
        lon (last coords)]
    (println (str "Latitude: " lat " - " "Longitude: " lon))))

;; Another way to destructure close to JS
(defn print-coords-2
  [coords]
  (let [[lat lon] coords]
    (println (str "Latitude: " lat " - " "Longitude: " lon))))

(let [[a b c] [1 2 3]]
  (println a b c))

(let [[a b c] '(1 2 3)]
  (println a b c))

(print-coords coords)
(print-coords-2 coords)

;; Destructuring a map
(def airport-coordinates
  {:lat 48.9615 :lon 2.4372 :code 'LFPB' :name "Paris Le Bourget Airport"})

(defn print-airport-coords
  [airport]
  (let [lat (:lat airport)
        lon (:lon airport)
        name (:name airport)]
    (println (str name " is located at Latitude: " lat " - " "Longitude: " lon))))

;; with associative destructuring
(defn print-airport-coords-2
  [airport]
  (let [{lat :lat lon :lon airport-name :name} airport]
    (println (str airport-name " is located at Latitude: " lat " - " "Longitude: " lon))))

;; When the keys and symbols can all have the same name
(defn print-airport-coords-3
  [airport]
  (let [{:keys [lat lon name]} airport]
    (println (str name " is located at Latitude: " lat " - " "Longitude: " lon)))
  (println "Have a nice trip"))

(print-airport-coords airport-coordinates)
(print-airport-coords-2 airport-coordinates)
(print-airport-coords-3 airport-coordinates)