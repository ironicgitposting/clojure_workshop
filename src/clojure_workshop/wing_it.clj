(ns clojure-workshop.wing-it
  (:require [clojure.math.numeric-tower :as math]))

(def sample-itinary {
                     ;Nantes
                     :from {
                            :lat 47.21
                            :lon  -1.55
                            }
                     ; Paris
                     :to {
                          :lat 48.86
                          :lon 2.334
                          }
                     ; In euros
                     :cost 45
                     ; In hours
                     :duration :driving
                     :vehicle :toyota
                     })


(defn delta
  [x1 x2]
  (math/expt (- x2 x1) 2))

(defn compute-euclidian-distance
  "Compute the euclidian disance using coordinates (:lat and :lon) from
  maps from-coords and to-coords"
  [from-coords to-coords]
  (let [lat-1 (:lat from-coords)
        lon-1 (:lon from-coords)
        lat-2 (:lat to-coords)
        lon-2 (:lon to-coords)]
    (* 110.25
       (math/sqrt (math/abs (+  (delta lat-1 lat-2) (* (Math/cos lat-1) (delta lon-1 lon-2))))))))

(compute-euclidian-distance (:from sample-itinary) (:to sample-itinary))

;; To compute the cost in the case of the transport being :driving we will look at the :vehicle in the map
; :sportche consumes 0.12 liters of petrol per kilometer, costing 1.5€ per liter
; :tayato consumes 0.07 liters of petrol per kilometer, costing 1.5€ per liter
; :sleta consumes, 0.2 kilowatt hour (kwh) of electricity per kilometer, costing 0.1€ per kwh
;the cost should be 0 when transport is :walking
;
;to compute the duration, we consider an average driving speed of 70 km per hour and an average walking speed of
; 5km per hour