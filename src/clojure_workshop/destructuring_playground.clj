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
  ;; we are looking for the lat lon and name keys in the airport map
  (let [{:keys [lat lon name]} airport]
    (println (str name " is located at Latitude: " lat " - " "Longitude: " lon)))
  (println "Have a nice trip"))



(print-airport-coords airport-coordinates)
(print-airport-coords-2 airport-coordinates)
!!!
(print-airport-coords-3 airport-coordinates)

;;;; Exercise 3.01
;;;; Parsing Fly Vector's Data

;; A coordinate point is a tuple of latitude and longitude EG: [48.9615, 2.4372]
;; A flight is a tuple of two coordinate points EG [[48.9615, 2.4372],[37.742, -25.6976]]

;; A booking consists of some information followed by one or multiple flights (up to three)
;; The first item is the internal ID for the booking
;; The second is the name of the passenger
;; The third is some sensitive information about the passenger, we must not parse this
;; Ex:

(def booking
  [
   1425,
   "Bob Smith",
   "Allergic to unsalted peanuts only."
   [[48.9615, 2.4372], [37.742, -25.6976]],
   [[41.9615, 8.4372], [39.742, -24.6976]]
   ])

(let [[id customer-name sensitive-info flight-1 flight-2 flight-3] booking]
  (println id customer-name sensitive-info flight-1 flight-2 flight-3))

(let [big-booking (conj booking [[48.9615 2.4372] [37.742 -25.6976]], [[55.9615 2.4372] [-22.742 -25.6976]])
      [id customer-name _ _ _ flight-3] big-booking]
  (println id customer-name flight-3))

(let [[_ customer-name _ flight1 flight2 flight3] booking]
  (println customer-name flight1 flight2 flight3))

(defn print-flight
  [flight]
  (let [[[lat1 lon1] [lat2 lon2]] flight]
    (println "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2)))

(defn print-flight-2
  [flight]
  (let [[departure arrival] flight
        [lat1 lon1] departure
        [lat2 lon2] arrival]
    (println "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2)))

(print-flight [[48.9615, 2.4372] [37.742 -25.6976]])
(print-flight-2 [[48.9615, 2.4372] [37.742 -25.6976]])

(defn print-booking
  [booking]
  (let [[_ customer-name _ & flights] booking]
    (println (str customer-name " booked " (count flights) " flights."))
    (let [[flight1 flight2 flight3] flights]
      (when flight1 (print-flight-2 flight1))
      (when flight2 (print-flight-2 flight2))
      (when flight3 (print-flight-2 flight3)))))

;;; Parsing MapJet Data with associative Destructuring
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

(let [{:keys [customer-name flights]} mapjet-booking]
  (println (str customer-name " booked " (count flights) " flights.")))

(defn print-mapjet-flight
  [flight]
  (let [{:keys [from to]} flight
        {lat1 :lat lon1 :lon} from
        {lat2 :lat lon2 :lon} to]
    (println "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2)))

(defn print-flights-test-mapjet
  [booking]
  (let [{:keys [customer-name flights]} booking]
    (println (str customer-name " booked " (count flights) " flights."))
    (map print-mapjet-flight flights)))

(print-flights-test-mapjet mapjet-booking)