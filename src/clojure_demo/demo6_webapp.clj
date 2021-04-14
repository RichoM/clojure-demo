(ns clojure-demo.demo6-webapp
  (:require [clojure.pprint :refer [pprint]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [response]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]
            [hiccup.page :as page]))

(comment

 ;; HTTP request/response

 (defn handler [request]
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body "Hello world"})

 (def server (jetty/run-jetty #'handler
                              {:port 3000, :join? false}))

 (.stop server)

 ;; Middleware: wrap-pàrams, wrap-json-body, wrap-json-response

 (def server (jetty/run-jetty (wrap-params #'handler)
                              {:port 3000, :join? false}))


 ;; Routing

 (defroutes app
   (GET "/" [] "<h1>Richo capo!</h1>")
   (POST "/" {:keys [params]} (do
                                (pprint params)
                                (response "OK")))

   ; REST
   (GET "/api/pokedex" [] (response pokedex))
   (GET "/api/pokedex/:id" [id] (response (find-pokemon id)))

   ; HTML
   (GET "/counter" [] (page/html5
                       [:p [:div#counter 0]]
                       [:p [:button#btn {:type "button"} "Press me!"]]
                       [:script "btn.onclick = function () { counter.innerText++; }"]))
   (GET "/pokemon/:id" [id] (pokemon-page (find-pokemon id)))
   (route/not-found "<h1>Page not found</h1>"))

 (def server (jetty/run-jetty (-> #'app
                                  wrap-params
                                  wrap-json-body
                                  wrap-json-response)
                              {:port 3000, :join? false}))

 (def pokedex (json/decode (slurp "pokedex.json") true))

 (defn find-pokemon [id]
   (first (filter (fn [p] (= id (str (:id p))))
                  pokedex)))

 (defn pokemon-page [{:keys [id name hp attack defense speed sp_attack sp_defense types]}]
   (layout
    [:div.container
     [:div.row
      [:div.col-auto [:h1 name]]
      [:div.col [:h1.fst-italic (str "#" id)]]]
     [:hr]
     [:div.row
      [:div.col
       [:h3 "Types"]
       [:ul (map (fn [t] [:li [:h4 t]]) types)]
       [:h3 "Base stats"]
       [:ul
        [:li [:h4 (str "HP: " hp)]]
        [:li [:h4 (str "Attack: " attack)]]
        [:li [:h4 (str "Defense: " defense)]]
        [:li [:h4 (str "Speed: " speed)]]
        [:li [:h4 (str "Sp. Attack: " sp_attack)]]
        [:li [:h4 (str "Sp. Defense: " sp_defense)]]]]
      [:div.col
       [:img.img-fluid {:src "https://i.pinimg.com/736x/bf/d8/d7/bfd8d7704cf357fdc06f003e8bfdc272.jpg"}]]]
     [:hr]
     [:div.row.justify-content-between
      [:div.col-auto [:a {:href (str "/pokemon/" (inc id))} "<< Previous"]]
      [:div.col-auto [:a {:href (str "/pokemon/" (dec id))} "Next >>"]]]]))


 (defn layout [& content]
   (page/html5
    [:head
     [:link {:href "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
             :rel "stylesheet"
             :integrity "sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
             :crossorigin "anonymous"}]]
    [:body
     content
     [:script {:src "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
               :integrity "sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
               :crossorigin "anonymous"}]]))
 ,)
