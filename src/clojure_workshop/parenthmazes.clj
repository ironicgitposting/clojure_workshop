(ns clojure-workshop.parenthmazes)

(defn welcome-multiplayer
  [friends]
  (when (seq friends)          ;; returns nil when the collection passed as a parameter is empty
    (let [nb-friends (count friends)]
      (println (str "Sending " nb-friends " fiend request" (when (> nb-friends 1) (str "s")) " to the following players: "
                    (clojure.string/join ", " friends))))))

(defn welcome
  ([player] (println (str "Welcome to Parenthmazes (single-player mode), " player "!")))
  ([player & friends]
   (println (str "Welcome to Parenthmazes (multiplayer mode, " player "!"))
   (welcome-multiplayer friends)))

(welcome "Jon" "Arya" "Tyrion" "Petyr")
(welcome "Jaqen")

(welcome "Jaqen" nil)

(def weapon-damage {:fist 10.0 :staff 35.0 :sword 100.0 :cast-iron-saucepan 150.0})

(defn strike
  [target weapon]
  (let [points (weapon weapon-damage)]
    (if (= :gnomes (:camp target))
      (update target :health + points)
      (update target :health - points))))

(def enemy
  {:name "Zulkaz" :health 250 :camp :trolls})

(def ally
  {:name "Carla" :health 80 :camp :gnomes})

(strike enemy :sword)                                       ;; will damage the target
(strike ally :staff)                                        ;; will heal the target