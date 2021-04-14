(ns clojure-demo.demo8-html
  (:require [hiccup.core :refer :all]
            [hiccup.page :as page]))

(comment

 ;; HTML

 (html [:div#foo.bar "Richo capo"])
 (html [:img {:src "imagen.jpg"}])

 (page/html5
  [:body
   [:p [:div#counter 0]]
   [:p [:button#btn {:type "button"} "Press me!"]]
   [:script "btn.onclick = function () { counter.innerText++; }"]])



 ,)
