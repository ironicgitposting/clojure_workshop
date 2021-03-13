(ns clojure-workshop.lazy-sequences-playground)

(def our-seq (range 1 100))

(first our-seq)   ;; only the first element of the sequence is realized (charged in memory),
                  ;; the rest doesn't exist until it is realized

(last our-seq)                                              ;;99, the entire sequence as been realized (or charged in memory)

;; functions such as map, filter and remove are also lazy
;; If you call them on a lazy sequence, they do not force the calculation of the entire sequence

;; count, sort, last are not lazy since they need to process the entire seq to work

;(range)                     ;; 0 to infinity (will blow up the jvm or the repl)
(->> (range)
     (map #(* 10 %))
     (take 5))               ;; only 5 elements from the infinite lazy seq will be realized

;1 - Create a lazy sequence with repeatedly

; infinite sequence of random numbers
(def our-randoms
  (repeatedly
    (partial rand-int 100)))

(take 20 our-randoms)

; wrap into a function
(defn some-random-integer
  [n]
  (take n (repeatedly (partial rand-int 100))))

(->> (some-random-integer 20)
     (filter odd?)
     (vec))

(map (partial * 10) [1 2 3 4 5])                            ; (10 20 30 40 50)
(reduce (partial * 10) [1 2 3 4 5])                         ; 1200000

(def multiply-by-10
  (partial * 10))                                           ;; 480

;; exercise 4.06


(def game-users
  [{:id 9342
    :username "speedy"
    :current-points 45
    :remaining-lives 2
    :experience-level 5
    :status :active}
   {:id 9854
    :username "stealthy"
    :current-points 1201
    :remaining-lives 1
    :experience-level 8
    :status :speed-boost}
   {:id 3014
    :username "sneaky"
    :current-points 725
    :remaining-lives 7
    :experience-level 3
    :status :active}
   {:id 2051
    :username "forgetful"
    :current-points 89
    :remaining-lives 4
    :experience-level 5
    :status :imprisoned}
   {:id 1032
    :username "wandering"
    :current-points 2043
    :remaining-lives 12
    :experience-level 7
    :status :speed-boost}
   {:id 7213
    :username "slowish"
    :current-points 143
    :remaining-lives 0
    :experience-level 1
    :status :speed-boost}
   {:id 5633
    :username "smarter"
    :current-points 99
    :remaining-lives 4
    :experience-level 4
    :status :terminated}
   {:id 3954
    :username "crafty"
    :current-points 21
    :remaining-lives 2
    :experience-level 8
    :status :active}
   {:id 7213
    :username "smarty"
    :current-points 290
    :remaining-lives 5
    :experience-level 12
    :status :terminated}
   {:id 3002
    :username "clever"
    :current-points 681
    :remaining-lives 1
    :experience-level 8
    :status :active}])

; 1 use map to return a vector of :current-points for each user
(->> game-users
     (map :current-points)
     (into []))

;; or
(into [] (map (fn [player] (:current-points player)) game-users))

;; extract :username and :current-points after filtering out :terminated users
(defn get-name-and-current-points
  [m]
  (let [{:keys [username current-points]} m]
    {:username username :current-points current-points}))

(->> game-users
     (filter #(= (:status %) :active))
     (map  get-name-and-current-points)
     (into []))
