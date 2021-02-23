(ns clojure-workshop.gemstones)

(def gemstone-db {
                  :ruby    {
                            :name       "Ruby"
                            :stock      480
                            :sales      [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
                            :properties {
                                         :dispersion       0.018
                                         :hardness         9.0
                                         :refractive-index [1.77 1.78]
                                         :color            "Red"
                                         }

                            }
                  :emerald {
                            :name       "Emerald"
                            :stock      85
                            :sales      [6605 2373 104 4764 9023]
                            :properties {
                                         :dispersion       0.014
                                         :hardness         7.5
                                         :refractive-index [1.57 1.58]
                                         :color            "Green shades to colorless"
                                         }
                            }
                  :diamond {
                            :name       "Diamond"
                            :stock      9,
                            :sales      [8295 329 5960 6118 4189 3436 9833 8870]
                            :properties {
                                         :dispersion       0.044
                                         :hardness         10
                                         :refractive-index [2.417 2.419]
                                         :color            "Typically yellow, brown, or gray to colorless"
                                         }
                            }
                  })

(defn durability
  [db gemstone]
  (get-in db [gemstone :properties :hardness]))

(durability gemstone-db :ruby)

;; update the color of a gem
(defn change-gemstone-color
  [db gemstone new-color]
  (assoc-in db [gemstone :properties :color] new-color))

;; record a sell
(defn sell
  [db gemstone client-id]
  (let [clients-updated-db (update-in db [gemstone :sales] conj client-id)]
    (update-in clients-updated-db [gemstone :stock] dec)))

(change-gemstone-color gemstone-db :ruby "Near colorless through pink through all shades of red to a deep crimson")
