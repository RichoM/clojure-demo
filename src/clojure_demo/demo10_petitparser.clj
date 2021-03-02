(ns clojure-demo.demo10-petitparser
  (:require [petitparser.core :as pp]))

(comment

 ;; Parser
 (def grammar {:start (pp/end :term)
               :term (pp/or :add :prod)
               :prod (pp/or :mul :prim)
               :prim (pp/or :parens :number)
               :add [:prod (pp/trim "+") :term]
               :mul [:prim (pp/trim "*") :prod]
               :parens [(pp/trim "(") :term (pp/trim ")")]
               :number (pp/trim (pp/flatten (pp/plus pp/digit)))})

 (def transformations {:number read-string
                       :parens second
                       :add (fn [[a _ b]] (+ a b))
                       :mul (fn [[a _ b]] (* a b))})

 (def parser (pp/compose grammar transformations))

 (pp/parse parser "1 + 2 * 3")
 (pp/parse parser "(1 + 2) * 3")
 (pp/parse parser "1 + (1 + 2) * 3 + 4")
 (pp/parse parser "1 + (1 + 2) * (3 + 4)")

 ,)
