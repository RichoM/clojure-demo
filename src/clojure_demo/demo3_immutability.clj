(ns clojure-demo.demo3-immutability
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]))

(comment

 ;; How to modify immutable objects?
 ;; (conj, disj, assoc, dissoc, assoc-in, update, update-in, ...)

 (conj [1 2 3] 4)
 (pop [1 2 3 4])

 (conj '(1 2 3) 4)
 (pop '(1 2 3 4))


 (contains? #{1 2 3 4} 5)
 (conj #{1 2 3 4} 5)
 (disj #{1 2 3 4 5} 5)


 (assoc {:a 1, :b 2}
        :c 3)

 (dissoc {:a 1, :b 2, :c 3} :b)

 (select-keys {:a 1, :b 2, :c 3, :d 4, :e 5}
              [:a :b])





 (def richo {:first-name "Jorge"
             :middle-name "Ricardo"
             :last-name "Moran"
             :birth-date {:year 1988, :month 4, :day 7}
             :addresses ["Avenida Siempreviva 742"
                         "221B Baker Street"
                         "Privet Drive 4"]})

 (pprint (assoc richo :nick-name "Richo"))


 (pprint (assoc-in richo
                   [:birth-date :day-name]
                   "Jueves"))

 (update {:count 0}
         :count inc)

 (pprint (update-in richo
                    [:addresses 2] str/upper-case))
 







 ,)
