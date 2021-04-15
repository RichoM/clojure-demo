(ns clojure-demo.demo6-webapp
  (:require [clojure.pprint :refer [pprint]]
            [clojure.string :as str]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [response]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]
            [hiccup.page :as page]
            [conman.core :refer [connect! disconnect! bind-connection] :as conn]))

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
   #_(GET "/api/pokedex" [] (response pokedex))
   #_(GET "/api/pokedex/:id" [id] (response (find-pokemon id)))

   ; HTML
   #_(GET "/counter" [] (page/html5
                       [:body
                        [:p [:div#counter 0]]
                        [:p [:button#btn {:type "button"} "Press me!"]]
                        [:script "btn.onclick = function () { counter.innerText++; }"]]))

   #_(GET "/pokemon/:id" [id] (pokemon-page (find-pokemon id)))
   (route/not-found "<h1>Page not found</h1>"))

 (def server (jetty/run-jetty (-> #'app
                                  wrap-params
                                  wrap-json-body
                                  wrap-json-response)
                              {:port 3000, :join? false}))

 (def pokedex (json/decode (slurp "pokedex.json") true))

 (take 9 pokedex)

 (defn find-pokemon [id]
   (first (filter (fn [p] (= id (str (:id p))))
                  pokedex)))

 (defn pokemon-page [{:keys [id name hp attack defense speed sp_attack sp_defense types]}]
   (if id
     (layout
      [:div.row
       [:div.col-auto [:h1.fst-italic (str "#" id)]]
       [:div.col-auto [:h1 name]]]
      [:hr]
      [:div.row
       [:div.col.fs-3
        [:div.row [:div.col "Types:"] [:div.col-sm.fw-light (str/join ", " types)]]
        [:div.mt-4.fw-bold "Base stats"]
        [:div.row [:div.col "HP:"] [:div.col-sm.fw-light hp]]
        [:div.row [:div.col "Attack:"] [:div.col-sm.fw-light attack]]
        [:div.row [:div.col "Defense:"] [:div.col-sm.fw-light defense]]
        [:div.row [:div.col "Speed:"] [:div.col-sm.fw-light speed]]
        [:div.row [:div.col "Sp. Attack:"] [:div.col-sm.fw-light sp_attack]]
        [:div.row [:div.col "Sp. Defense:"] [:div.col-sm.fw-light sp_defense]]]
       [:div.col
        [:img.img-fluid {:src (str "https://raw.githubusercontent.com/fanzeyi/pokemon.json/master/images/" (format "%03d" id) ".png")}]]]
      [:hr]
      [:div.row.justify-content-between
       [:div.col-auto [:a {:href (str "/pokemon/" (dec id))} "&laquo; Previous"]]
       [:div.col-auto [:a {:href (str "/pokemon/" (inc id))} "Next &raquo;"]]])))


 (defn layout [& content]
   (page/html5
    [:head
     [:link {:href "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
             :rel "stylesheet"
             :integrity "sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
             :crossorigin "anonymous"}]]
    [:body
     [:div.container content]
     [:script {:src "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
               :integrity "sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
               :crossorigin "anonymous"}]]))

 (def ^:dynamic *db* (connect! {:jdbc-url "jdbc:mysql://localhost:3306/pokedex?useSSL=false&user=guest&password=turingturing"}))
 (bind-connection *db* "clojure_demo/queries.sql")

 (defn find-pokemon [id]
   (if-let [pokemon (get-pokemon-by-id {:id id})]
     (assoc pokemon :types (mapv :name (get-pokemon-types pokemon)))))

 (add-pokemon-type! {:id 4, :type "Water"})

 (disconnect! *db*)

 ,)
