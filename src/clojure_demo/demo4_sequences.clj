(ns clojure-demo.demo4-sequences)

(comment

 ;; Sequences (map, filter, remove, reduce, ...)
 (map inc [1 2 3 4])
 (filter even? [1 2 3 4 5 6 7 8 9])
 (remove odd? [1 2 3 4 5 6 7 8 9])
 (reduce * [1 2 3 4 5])

 (set! *print-length* 1000)

 (range)
 (take 100 (filter odd? (range)))


 ,)
