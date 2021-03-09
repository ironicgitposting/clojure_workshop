(ns clojure-workshop.wing-it
  (:require [clojure.math.numeric-tower :as math]))

;; This is an exercise, euclidean distance only works for planes
;; For cars you need to use the haversine formula or the spherical law of cosines
;; https://en.wikipedia.org/wiki/Haversine_formula

;; To compute the cost in the case of the transport being :driving we will look at the :vehicle in the map
; :sportche consumes 0.12 liters of petrol per kilometer, costing 1.5€ per liter
; :tayato consumes 0.07 liters of petrol per kilometer, costing 1.5€ per liter
; :sleta consumes, 0.2 kilowatt hour (kwh) of electricity per kilometer, costing 0.1€ per kwh
;the cost should be 0 when transport is :walking
;
;to compute the duration, we consider an average driving speed of 70 km per hour and an average walking speed of
; 5km per hour


(def paris {:lat 48.856483 :lon 2.352413})

(def bordeaux {:lat 44.834999 :lon -0.575490})

;; in km/h
(def average-walking-speed 5)
(def average-driving-speed 70)

;; in euros
(def petrol-price-per-liter 1.50)

(defn compute-euclidean-distance
  "Compute the euclidean distance using coordinates (:lat and :lon) from
  maps from-coords and to-coords"
  [from-coords to-coords]
  (let [lat-1 (:lat from-coords)
        lon-1 (:lon from-coords)
        lat-2 (:lat to-coords)
        lon-2 (:lon to-coords)
        cos-lat-1 (Math/cos lat-1)
        delta-lat (math/expt (- lat-2 lat-1) 2)
        delta-lon (math/expt (- lon-2 lon-1) 2)]
    (* 110.25 (Math/sqrt (+ delta-lat (* cos-lat-1 delta-lon))))))

(defmulti itinary :transport)

(defmethod itinary :walking
  [m]
  (let [from (:from m)
        to (:to m)
        distance (compute-euclidean-distance from to)
        duration (with-precision 2 (/ distance average-walking-speed))]
    {:cost 0 :distance distance  :duration duration}))

(defmulti cost-by-type-of-car :vehicle)

(defmethod cost-by-type-of-car :tayato
  ;; 0.07 liters of petrol per kilometer
  [m]
  (let [distance (:distance m)
        liter-consumption (* distance 0.07)
        cost (* petrol-price-per-liter liter-consumption)]
    cost))

(defmethod cost-by-type-of-car :sportche
  ;; 0.12 liters of petrol per kilometer
  [m]
  (let [distance (:distance m)
        liter-consumption (* distance 0.12)
        cost (* petrol-price-per-liter liter-consumption)]
    cost))

(defmethod cost-by-type-of-car :sleta
  ;; 0.2 kilowatt hour (kwh) of electricity per kilometer, costing 0.1€ per kwh
  [m]
  (let [distance (:distance m)
        kwh (* distance 0.2)
        cost (* 0.1 kwh)]
    cost))


(defmethod itinary :driving
  [m]
  (let [from (:from m)
        to (:to m)
        distance (compute-euclidean-distance from to)
        duration (with-precision 2 (/ distance average-driving-speed))
        cost (cost-by-type-of-car {:distance distance :vehicle (:vehicle m)})]
    {:cost cost :distance distance  :duration duration}))

(itinary {:from paris :to bordeaux, :transport :driving :vehicle :tayato})