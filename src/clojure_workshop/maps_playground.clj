(ns clojure-workshop.maps-playground)

;; Maps manipulation
(into {} [[:artist "David Bowie"] [:song "Space Oddity"]])
(hash-map :artist "David Bowie")

;; get value from map
(:album artist)
(get artist :album)
;; Fallback value
(get artist :rating)                                        ;; nil
(get artist :rating "Very Good")
(:rating artist "Very Good")

;; Create a new map by adding a key value pair to an existing map.
;; Its not the same map!
(assoc artist :year 1969)
(assoc artist :rating "Very good")

;; Merge two maps together
(merge artist {:year 1969})

;; You can nest maps
 {
  "David Bowie" {
               "The Man Who Mapped the World" {:year 1970, :duration "4:01"}
                "Comma Oddity" {:year 1970, :duration "4:29"}
                }
 }


;; update map value
(def artist {:artist "David Bowie" :album "Space Oddity" :year 1968})
(def artist (assoc artist :album "Love You till Tuesday"))
(update artist :year dec)
(update artist :year - 1)

;; remove one or more elements from a map
(dissoc artist :album :year)