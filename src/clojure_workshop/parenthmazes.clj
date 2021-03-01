(ns clojure-workshop.parenthmazes)

(defn welcome
  ([player] (println (str "Welcome to Parenthmazes (single-player mode), " player "!")))
  ([player & friends]
   (let [nb-friends (count friends)]
     (println (str "Welcome to Parenthmazes (multiplayer mode, " player "!"))
     (println (str "Sending " nb-friends " fiend request" (when (> nb-friends 1) (str "s"))  " to the following players: "
                   (clojure.string/join ", " friends))))))


(welcome "Jon" "Arya" "Tyrion" "Petyr")