(ns clojure-demo.demo14-multimethods)

(comment

 ;; Multimethods are flexible

 (defmulti encounter (fn [x y] [(:species x) (:species y)]))

 (defmethod encounter [:bunny :lion] [b l] :run-away)
 (defmethod encounter [:lion :bunny] [l b] :eat)
 (defmethod encounter [:lion :lion] [l1 l2] :fight)
 (defmethod encounter [:bunny :bunny] [b1 b2] :mate)

 (def bugs {:species :bunny, :name "Bugs Bunny"})
 (def lola {:species :bunny, :name "Lola Bunny"})
 (def mufasa {:species :lion, :name "Mufasa"})
 (def scar {:species :lion, :name "Scar"})

 (encounter bugs mufasa)
 (encounter scar bugs)
 (encounter mufasa scar)
 (encounter bugs lola)

 ,)
