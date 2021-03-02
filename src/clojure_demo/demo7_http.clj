(ns clojure-demo.demo7-http
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]))

(comment

 ;; HTTP request/response

 (defn handler [request]
   (println "ACAACA")
   (clojure.pprint/pprint request)
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body "Richo capo"})

 (def server (jetty/run-jetty #'handler
                              {:port 3000, :join? false}))

 (.stop server)


 (def server (jetty/run-jetty (wrap-params #'handler)
                              {:port 3000, :join? false}))

 ,)
