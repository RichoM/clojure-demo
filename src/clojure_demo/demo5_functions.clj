(ns clojure-demo.demo5-functions
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(comment

 ;; How to define functions? (fn, defn)
 (defn shout [msg]
   (str (str/upper-case msg) "!"))

 (shout "Richo capo")




 (defn square [x] (* x x))

 (map square (range 1 10))


 (map (fn [n] (* n n))
      (range 1 10))


 (map #(* % %)
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








 ;; Destructuring
 (defn distance [point-1 point-2]
   (Math/sqrt (+ (square (- (:x point-2) (:x point-1)))
                 (square (- (:y point-2) (:y point-1))))))

 (distance {:x 1, :y 2}
           {:x 4, :y 6})


 ;; Associative destructuring
 (defn distance [{x1 :x, y1 :y} {x2 :x, y2 :y}]
   (Math/sqrt (+ (square (- x2 x1))
                 (square (- y2 y1)))))

 (distance {:x 1, :y 2}
           {:x 4, :y 6})



 ;; Sequential destructuring
 (defn distance [[x1 y1] [x2 y2]]
   (Math/sqrt (+ (square (- x2 x1))
                 (square (- y2 y1)))))

 (distance [1 2] [4 6])


 ,)
