(ns clojure-demo.demo5-functions
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(comment

 ;; How to define functions? (fn, defn)
 (defn shout [msg]
   (str (str/upper-case msg) "!"))

 (shout "Richo capo")


 (fn [n] (* n n))

 (map (fn [n] (* n n))
      (range 1 10))



 (remove (fn [path] (str/starts-with? path "D:\\UAI\\GIRA\\UziScript\\.git"))
         (map (fn [file] (.getPath file))
              (file-seq (io/file "D:\\UAI\\GIRA\\UziScript"))))

 (filter (fn [file] (str/ends-with? (.getName file) ".clj"))
         (file-seq (io/file "D:\\UAI\\GIRA\\UziScript")))



 (defn factorial [n]
   (if (<= n 1)
     n
     (* n
        (factorial (dec n)))))

 (factorial 5)
 (map factorial (range 0 10))

 (factorial 1000)
 (/ (factorial 1000) (factorial 999))

 (factorial 10000)




 (defn factorial [n]
   (reduce *' (take-while pos-int? (iterate dec n))))

 (defn factorial [n]
   (->> n
        (iterate dec)
        (take-while pos-int?)
        (reduce *')))


(defn factorial [n]
  (loop [i 1, f 1]
    (if (> i n)
      f
      (recur (inc i) (*' i f)))))

  (factorial 10000)
 ,)
