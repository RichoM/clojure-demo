(ns clojure-demo.demo1-basic)

(comment

 ;; Primitive objects (numbers, ratios, strings, regex, keywords, ...)
 42
 3.141592
 1/3
 "Hola mundo!"
 #"\s*(\d+\.\d+\.\d+\.\d+|localhost)\:(\d+)\s*"
 :name


 ;; Collections (list, vector, set, map)
 '(1 2 3 4)

 ["Richo" "Gonzalo" "Matías" "Sebastián"]

 #{1 2 3 4}

 {:first-name "Jorge"
  :middle-name "Ricardo"
  :last-name "Moran"
  :birth-date {:year 1998, :month 4, :day 7}
  :addresses ["Avenida Siempreviva 742"
              "221B Baker Street"
              "Privet Drive 4"]}

 ,)
