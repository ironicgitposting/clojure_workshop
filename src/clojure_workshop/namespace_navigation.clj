;;
;; NS CHEATSHEET
;;
;; * :require makes functions available with a namespace prefix
;;  and optionally can refer functions to the current ns.
;;
;; * :import refers Java classes to the current namespace.
;;
;; * :refer-clojure affects availability of built-in (clojure.core)
;;   functions.
;;
;; Updated: 12/09/2016 ~ghoseb

(ns clojure-workshop.namespace_navigation

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ;; Refers clojure.string into current NS, e.g.:
  ;;   (clojure.string/lower-case "FooBar)
  (:require [clojure.string])

  ;; Refers string into current NS, e.g.:
  ;;   (string/lower-case "FooBar")
  (:require [clojure.string :as string])

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ;; Refers all functions from clojure.string and
  ;; clojure.set into current NS, e.g.:
  ;;   (union #{:a :b} #{:a :c} #{:b :d})
  ;;   (lower-case "FooBar")
  (:require [clojure.string :refer :all]
            [clojure.set :refer :all])

  ;; Refers only lower-case and union into current NS, e.g.:
  ;;   (union #{:a :b} #{:a :c} #{:b :d})
  ;;   (lower-case "FooBar")
  (:require [clojure.string :refer [lower-case]]
            [clojure.set :refer [union]])

  ;; Refers ancestors & descendants into current NS as xml-ancestors & xml-descendants
  (:require clojure.data.zip)
  (:refer [clojure.data.zip :rename {ancestors xml-ancestors,
                                     descendants xml-descendants}])

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ;; Refers ArrayList and HashMap into current NS:
  (:import [java.util ArrayList HashMap])

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ;; Excludes built-in print
  (:refer-clojure :exclude [print])

  ;; Excludes all built-ins except print
  (:refer-clojure :only [print])

  ;; Renames built-in print to core-print
  (:refer-clojure :rename {print core-print})

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ;; Reload all dependencies while loading the ns
  (:require [clojure.string :as cs :refer [split join]] :reload)

  ;; Be verbose about loading the deps
  (:require [clojure.string :as cs :refer [split join]] :verbose)

  ;; There is also :reload-all that reloads every depenency in the ns
  (:require [clojure.string :refer [split join]] :reload-all)

  ;; Everything together
  (:require [clojure.string :as cs :refer [split join]] :verbose :reload))