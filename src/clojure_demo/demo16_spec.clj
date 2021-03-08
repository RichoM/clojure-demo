(ns clojure-demo.demo16-spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

(comment

 ::richo

 (s/valid? even? 10)

 (defn make-circle [r] {:type :circle, :radius r})
 (defn make-triangle [b h] {:type :triangle, :base b, :height h})
 (defn make-square [s] {:type :square, :side s})
 (defn make-rectangle [l w] {:type :rectangle, :length l, :width w})

 (s/def ::radius number?)
 (s/def ::base number?)
 (s/def ::height number?)
 (s/def ::side number?)
 (s/def ::length number?)
 (s/def ::width number?)

 (s/def ::circle (s/keys :req-un [::radius]))
 (s/def ::triangle (s/keys :req-un [::base ::height]))
 (s/def ::square (s/keys :req-un [::side]))
 (s/def ::rectangle (s/keys :req-un [::length ::width]))

 (s/valid? ::circle (make-circle 10))
 (s/valid? ::circle (make-square 10))

 (s/explain-data ::circle (make-circle "R"))
 (s/explain-data ::circle "Richo")


 (gen/generate (s/gen ::rectangle))








 (def palo? #{:oro :espada :copa :basto})
 (def número? (into #{:sota :caballo :rey :ancho}
                    (range 2 10)))
 (def mazo (for [palo palo?
                 num número?]
             [num palo]))

 (s/def ::carta (s/tuple número? palo?))
 (s/def ::mano (s/* ::carta))

 (s/def ::nombre string?)
 (s/def ::puntaje int?)
 (s/def ::jugador (s/keys :req [::nombre ::puntaje ::mano]))

 (s/def ::jugadores (s/* ::jugador))
 (s/def ::mazo (s/* ::carta))
 (s/def ::juego (s/keys :req [::jugadores ::mazo]))


 (def juego {::mazo mazo
             ::jugadores [{::nombre "Richo!"
                           ::puntaje 100
                           ::mano []}
                          {::nombre "Juan"
                           ::puntaje 50
                           ::mano []}]})

 (defn repartir [juego]
   ,,,)

 (s/valid? ::jugador (get-in juego [::jugadores 0]))

 (s/valid? ::juego juego)

 (gen/generate (s/gen ::juego))























 ,)
