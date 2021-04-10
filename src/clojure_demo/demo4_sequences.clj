(ns clojure-demo.demo4-sequences
  (:require [clojure.java.io :as io]))

(comment

 ;; Sequences (map, filter, remove, reduce, ...)
 (map inc [1 2 3 4])

 (filter even? [1 2 3 4 5 6 7 8 9])

 (remove odd? [1 2 3 4 5 6 7 8 9])

 (reduce * [1 2 3 4 5])



 ;; Sequences can be infinite
 (set! *print-length* 100)

 (range)
 (take 10 (filter odd? (range)))

 (repeatedly rand)
 (iterate inc 0)
 (file-seq (io/file "D:\\UAI\\GIRA\\UziScript"))








 ,)
