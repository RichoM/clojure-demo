(ns clojure-demo.demo13-polymorphism)

(comment

 ;; What about polymorphism?
 ;; Geometric shapes example

 (defn make-circle [r] {:type :circle, :radius r})
 (defn make-triangle [b h] {:type :triangle, :base b, :height h})
 (defn make-square [s] {:type :square, :side s})
 (defn make-rectangle [l w] {:type :rectangle, :length l, :width w})

 (defn area [shape]
   (case (:type shape)
     :circle (* Math/PI
                (:radius shape)
                (:radius shape))
     :triangle (* 1/2
                  (:base shape)
                  (:height shape))
     :square (* (:side shape)
                (:side shape))
     :rectangle (* (:length shape)
                   (:width shape))
     (throw (Exception. "Unknown shape"))))

 (area (make-rectangle 3 5))
 (area (make-triangle 2 3))

 ;; Cuál es el problema con esta implementación?
 ;; Qué pasa si la quiero extender con una nueva figura geométrica?














 ;; Enter multimethods!

 (defmulti area :type)

 (defmethod area :circle [shape]
   (* Math/PI
      (:radius shape)
      (:radius shape)))

 (defmethod area :triangle [shape]
   (* 1/2
      (:base shape)
      (:height shape)))

 (defmethod area :square [shape]
   (* (:side shape)
      (:side shape)))

 (defmethod area :rectangle [shape]
   (* (:length shape)
      (:width shape)))

 (defmethod area :default [shape]
   (throw (Exception. "Unknown shape")))

 (area (make-rectangle 3 4))


 ,)
