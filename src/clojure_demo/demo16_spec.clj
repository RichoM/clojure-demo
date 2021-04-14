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


 (gen/generate (s/gen ::circle))
 (gen/generate (s/gen ::triangle))








 (def palo? #{:oro :espada :copa :basto})
 (def número? (into #{:sota :caballo :rey :ancho}
                    (range 2 10)))


 (defn distinct-or-empty? [coll]
   (or (empty? coll)
       (apply distinct? coll)))

 (s/def ::carta (s/tuple número? palo?))
 (s/def ::mano (s/and (s/* ::carta)
                      distinct-or-empty?))

 (s/def ::nombre string?)
 (s/def ::puntaje (s/or :pos pos-int?
                        :zero zero?))
 (s/def ::jugador (s/keys :req [::nombre ::puntaje ::mano]))

 (s/def ::jugadores (s/and (s/+ ::jugador)
                           #(< (count %) 5)))
 (s/def ::mazo (s/and (s/* ::carta)
                      distinct-or-empty?))

 (defn all-cards [{:keys [::mazo ::jugadores]}]
   (into mazo (mapcat ::mano jugadores)))

 (s/def ::juego (s/and (s/keys :req [::jugadores ::mazo])
                       (fn [juego]
                         (distinct-or-empty? (all-cards juego)))))



 (def juego {::mazo (for [palo palo?
                          num número?]
                      [num palo])
             ::jugadores [{::nombre "Richo!"
                           ::puntaje 100
                           ::mano []}
                          {::nombre "Juan"
                           ::puntaje 50
                           ::mano []}]})



 (s/valid? ::jugador (get-in juego [::jugadores 0]))

 (s/valid? ::jugador {::nombre "Richo!"
                      ::puntaje 100
                      ::mano [[4 :basto]
                              [2 :oro]
                              [2 :oro]]})

 (s/explain ::jugador {::nombre "Richo!"
                       ::puntaje 100
                       ::mano [[4 :basto]
                               [2 :oro]
                               [2 :oro]]})

 (s/valid? ::juego juego)

 (gen/generate (s/gen ::juego))
 (gen/generate (s/gen ::jugador))

 (s/exercise ::jugador)
 (s/exercise ::juego)



 (defn repartir [{:keys [::jugadores ::mazo] :as juego}]
   (let [manos (zipmap jugadores
                       (partition-all (/ (count mazo)
                                         (count jugadores))
                                      (shuffle mazo)))]
     (assoc juego
            ::mazo []
            ::jugadores (map #(assoc % ::mano (manos %))
                             jugadores))))

 (s/fdef repartir
         :args (s/cat :juego ::juego)
         :ret ::juego
         :fn #(= (count (all-cards (-> % :args :juego)))
                 (count (all-cards (-> % :ret)))))


 (repartir juego)


 ;; TODO(Richo): Cómo instrumentar el código para que valide la invariante??











 ,)
