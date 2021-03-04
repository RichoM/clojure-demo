(ns clojure-demo.demo15-protocols)

(comment

 ;; Protocols/Types/Records

 (defprotocol Shape
   (area [self]))

 (deftype Circle [radius]
   Shape
   (area [self] (* Math/PI radius radius)))

 (deftype Triangle [base height]
   Shape
   (area [self] (* 1/2 base height)))

 (deftype Square [side]
   Shape
   (area [self] (* side side)))

 (deftype Rectangle [length width]
   Shape
   (area [self] (* length width)))


 (area (Circle. 5))
 (area (Triangle. 3 4))

 (area (->Triangle 3 4))

 (map (comp area ->Square)
      (range 10))

 (extend-type java.lang.Number
   Shape
   (area [self] "Los números no tienen área"))

 (area 10)

 (extend-type java.lang.String
   Shape
   (area [self] "Los strings tampoco tienen área!"))

 (area "Richo")

 (map area
      [1
       "Richo"
       (->Rectangle 4 3)])


 ,)
