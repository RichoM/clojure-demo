(ns clojure-demo.demo5-functions
  (:require [clojure.string :as str]))

(comment

 ;; How to define functions? (fn, defn)
 (defn shout [msg]
   (str (str/upper-case msg) "!"))

 (shout "Richo capo")

 (defn factorial [n]
   (if (<= n 1)
     n
     (* n
        (factorial (dec n)))))

 (factorial 5)
 (map factorial (range 0 10))

 (factorial 1000)
 (/ (factorial 1000) (factorial 999))

 (map (fn [n] (* n n))
      (range 1 10))

 ,)
