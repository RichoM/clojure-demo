(ns clojure-demo.demo6-json
  (:require [clojure.pprint :refer [pprint]]
            [cheshire.core :as json]))

(comment

 ;; Maps are used to represent almost everything
 ;; JSON

 (pprint (json/decode (slurp "data.json")))

 (def richo {:first-name "Jorge"
             :middle-name "Ricardo"
             :last-name "Moran"
             :birth-date {:year 1998, :month 4, :day 7}
             :addresses ["Avenida Siempreviva 742"
                         "221B Baker Street"
                         "Privet Drive 4"]})


 (spit "richo.json" (json/encode richo {:pretty true}))

 ,)
