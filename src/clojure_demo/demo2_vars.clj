(ns clojure-demo.demo2-vars)

(comment

 ;; Vars
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



 ,)
