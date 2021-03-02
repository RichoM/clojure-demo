(ns clojure-demo.demo6-json
  (:require [cheshire.core :as json]))

(comment

 ;; Maps are used to represent almost everything
 ;; JSON

 (json/decode (slurp "data.json"))
 (spit "richo.json" (json/encode richo {:pretty true}))

 ,)
