(ns clojure-workshop.encode-decode)


(defn encode-letter
  [s x]
  (let [code (Math/pow (+ x (int (first (char-array s)))) 2)]
    (str "#" (int code))))

(defn decode-letter
  [s x]
  (let [enc-c (Integer/parseInt (subs s 1))
        letter (char (- (int (Math/sqrt enc-c)) x))]
    (str letter)))

(defn encode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\w" (fn [s] (encode-letter s number-of-words)))))

(defn decode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\#\d+" (fn [s] (decode-letter s number-of-words)))))

(def test-message (encode "If you want to keep a secret, you must also hide it from yourself"))
(decode test-message)