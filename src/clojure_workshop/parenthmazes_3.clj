(ns clojure-workshop.parenthmazes-3)

(def player {:name "Lea" :health 200 :position {:x 10 :y 10 :facing "north"}})

;; Create a move multimethod. The dispatch function should determine the dispatch value by retrieving :facing
;; in the position map of a player entity. The :facing value could be one of the following values
;; :north, :south, :west, :east

(defmulti move (fn [m] (get-in m [:position :facing])))     ;; we fetch the direction
;; another way to write that
;;(ns-unmap 'user 'move)
;;(defmulti move (comp :facing :position))

;; we write the method for a :north dispatch from the multimethod
(defmethod move "north"
  [entity]
  (update-in entity [:position :y] inc))

(defmethod move "south"
  [entity]
  (update-in entity [:position :y] dec))

(defmethod move "east"
  [entity]
  (update-in entity [:position :x] inc))

(defmethod move "west"
  [entity]
  (update-in entity [:position :x] dec))

(defn set-player-facing
  [player-m direction-k]
  (assoc-in player-m [:position :facing] direction-k))

(move player)
(def player (set-player-facing player "east"))
player
(move player)