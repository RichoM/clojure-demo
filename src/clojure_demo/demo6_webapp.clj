(ns clojure-demo.demo6-webapp
  (:require [clojure.pprint :refer [pprint]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [response]]
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

 ;; Middleware: wrap-pàrams, wrap-json-body, wrap-json-response

 (def server (jetty/run-jetty (wrap-params #'handler)
                              {:port 3000, :join? false}))


 ;; Routing

 (defroutes app
   (GET "/" [] "<h1>Richo capo!</h1>")
   (POST "/" {:keys [params]} (do (pprint params)
                                (response "OK")))
   (route/not-found "<h1>Page not found</h1>"))


 (def server (jetty/run-jetty (-> #'app
                                  wrap-params
                                  wrap-json-body
                                  wrap-json-response)
                              {:port 3000, :join? false}))


 ;; REST api
 (def richo {:first-name "Jorge"
             :middle-name "Ricardo"
             :last-name "Moran"
             :birth-date {:year 1998, :month 4, :day 7}
             :addresses ["Avenida Siempreviva 742"
                         "221B Baker Street"
                         "Privet Drive 4"]})

 (defroutes app
   (GET "/api/richo" [] (response [richo]))
   ;(POST "/api/richo" [])
   (route/not-found "<h1>Page not found</h1>"))

 (def server (jetty/run-jetty (-> #'app
                                  wrap-params
                                  wrap-json-body
                                  wrap-json-response)
                              {:port 3001, :join? false}))

 ,)
