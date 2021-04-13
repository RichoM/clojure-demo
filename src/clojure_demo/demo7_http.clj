(ns clojure-demo.demo7-http
  (:require [clojure.pprint :refer [pprint]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(comment

 ;; HTTP request/response

 (defn handler [request]
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body "Hello world"})

 (def server (jetty/run-jetty #'handler
                              {:port 3000, :join? false}))

 (.stop server)


 (def server (jetty/run-jetty (wrap-params #'handler)
                              {:port 3000, :join? false}))


 ;; Routing

 (defroutes app
   (GET "/" [] "<h1>Richo capo</h1>")
   (route/not-found "<h1>Page not found</h1>"))


 (def server (jetty/run-jetty (wrap-params #'app)
                              {:port 3000, :join? false}))
 ,)
