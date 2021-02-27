(ns clojure-demo.demo
  (:require [clojure.string :as str]))

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

 ;; Variables
 (def richo {:first-name "Jorge"
              :middle-name "Ricardo"
              :last-name "Moran"
              :birth-date {:year 1998, :month 4, :day 7}
              :addresses ["Avenida Siempreviva 742"
                          "221B Baker Street"
                          "Privet Drive 4"]})

 richo
 (:first-name richo)
 (:nick-name richo)
 (get richo :nick-name "Richo")
 (get-in richo [:birth-date :year])

 ;; How to modify immutable objects?
 ;; (conj, disj, assoc, dissoc, assoc-in, update, update-in, ...)
 ;; (map, filter, remove, reduce, ...)
 (conj [1 2 3] 4)
 (pop [1 2 3 4])
 (assoc {:a 1, :b 2}
        :c 3)

 (assoc-in {:first-name "Jorge"
            :middle-name "Ricardo"
            :last-name "Moran"
            :birth-date {:year 1998, :month 4, :day 7}
            :addresses ["Avenida Siempreviva 742"
                        "221B Baker Street"
                        "Privet Drive 4"]}
           [:birth-date :day-name]
           "Jueves")

 (update {:count 0} :count inc)

 (update-in {:first-name "Jorge"
            :middle-name "Ricardo"
            :last-name "Moran"
            :birth-date {:year 1998, :month 4, :day 7}
            :addresses ["Avenida Siempreviva 742"
                        "221B Baker Street"
                        "Privet Drive 4"]}
            [:addresses 2] str/upper-case)

 ;; How to define functions? (fn, def, defn)
 ;; factorial example (*, *')

 ;; Maps are used to represent almost everything
 ;; HTTP request/response
 ;; JSON
 ;; Database rows
 ;; HTML
 ;; Parser
 ;; Config

 ;; What about mutable state?
 ;; Atoms (@, swap!, reset!, ...)
 ;; Atoms are thread-safe (data race example C# vs clj)

 ;; What about polymorphism?
 ;; Geometric shapes example
 ;; Multimethods
 ;; Protocols/Types/Records

 ;; Future demos (macros? clojure.spec? core.async?)
 ,)
