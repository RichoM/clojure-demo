(ns clojure-demo.demo3-immutability
  (:require [clojure.string :as str]))

(comment

 ;; How to modify immutable objects?
 ;; (conj, disj, assoc, dissoc, assoc-in, update, update-in, ...)

 (conj [1 2 3] 4)
 (pop [1 2 3 4])
 (assoc {:a 1, :b 2}
        :c 3)

 (def richo {:first-name "Jorge"
             :middle-name "Ricardo"
             :last-name "Moran"
             :birth-date {:year 1998, :month 4, :day 7}
             :addresses ["Avenida Siempreviva 742"
                         "221B Baker Street"
                         "Privet Drive 4"]})

 (assoc richo :nick-name "Richo")
 (assoc-in richo
           [:birth-date :day-name]
           "Jueves")

 (update {:count 0} :count inc)

 (update-in richo
            [:addresses 2] str/upper-case)



 ,)
