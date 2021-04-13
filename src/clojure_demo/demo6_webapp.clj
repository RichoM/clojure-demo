(ns clojure-demo.demo6-webapp
  (:require [clojure.pprint :refer [pprint]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [response]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]))

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
   (GET "/api/pokemons" [] (response pokemons))
   (GET "/api/pokemons/:id" [id] (response (find-pokemon id)))

   ; HTML
   (GET "/pokemon" [] nil)
   (route/not-found "<h1>Page not found</h1>"))

 (def server (jetty/run-jetty (-> #'app
                                  wrap-params
                                  wrap-json-body
                                  wrap-json-response)
                              {:port 3000, :join? false}))

 (def pokemons (json/decode (slurp "pokedex.json") true))

 (defn find-pokemon [id]
   (first (filter (fn [p] (= id (str (:id p))))
                  pokemons)))


 ,)
