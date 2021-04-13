(ns clojure-demo.demo8-html
  (:require [hiccup.core :refer :all]
            [hiccup.page :as page]))

(comment

 ;; HTML

 (html [:div#foo.bar "Richo capo"])
 (html [:img {:src "imagen.jpg"}])

 (page/html5
  [:p [:div#counter 0]]
  [:p [:button#btn {:type "button"} "Press me!"]]
  [:script "btn.onclick = function () { counter.innerText++; }"])






 ;; Web server + HTML
 (require '[ring.adapter.jetty :as jetty])

 (defn handler [request]
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body (page/html5
           [:h1 [:div#counter 0]]
           [:p [:button#btn {:type "button"} "Press me!"]]
           [:script "btn.onclick = function () { counter.innerText++; }"])})


 (def server (jetty/run-jetty #'handler
                              {:port 3000, :join? false}))

 (.stop server)
 ,)
