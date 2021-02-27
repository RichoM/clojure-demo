(ns clojure-demo.demo
  (:require [clojure.string :as str]))

(comment
 ;; Primitive objects (numbers, ratios, strings, regex, keywords, ...)
 42
 3.141592
 1/3
 "Hola mundo!"
 #"\s*(\d+\.\d+\.\d+\.\d+|localhost)\:(\d+)\s*"
 :name

 ;; Collections (list, vector, set, map)
 '(1 2 3 4)

 ["Richo" "Gonzalo" "Matías" "Sebastián"]

 #{1 2 3 4}

 {:first-name "Jorge"
  :middle-name "Ricardo"
  :last-name "Moran"
  :birth-date {:year 1998, :month 4, :day 7}
  :addresses ["Avenida Siempreviva 742"
              "221B Baker Street"
              "Privet Drive 4"]}

 ;; Vars
 (def richo {:first-name "Jorge"
              :middle-name "Ricardo"
              :last-name "Moran"
              :birth-date {:year 1998, :month 4, :day 7}
              :addresses ["Avenida Siempreviva 742"
                          "221B Baker Street"
                          "Privet Drive 4"]})

 richo
 (:first-name richo)
 (:nick-name richo)
 (get richo :nick-name "Richo")
 (get-in richo [:birth-date :year])


 ;; How to modify immutable objects?
 ;; (conj, disj, assoc, dissoc, assoc-in, update, update-in, ...)

 (conj [1 2 3] 4)
 (pop [1 2 3 4])
 (assoc {:a 1, :b 2}
        :c 3)

 (assoc richo :nick-name "Richo")
 (assoc-in richo
           [:birth-date :day-name]
           "Jueves")

 (update {:count 0} :count inc)

 (update-in richo
            [:addresses 2] str/upper-case)


 ;; Sequences (map, filter, remove, reduce, ...)
 (map inc [1 2 3 4])
 (filter even? [1 2 3 4 5 6 7 8 9])
 (remove odd? [1 2 3 4 5 6 7 8 9])
 (reduce * [1 2 3 4 5])

 (set! *print-length* 1000)

 (range)
 (take 100 (filter odd? (range)))


 ;; How to define functions? (fn, defn)
 (defn shout [msg]
   (str (str/upper-case msg) "!"))

 (shout "Richo capo")

 (defn factorial [n]
   (if (<= n 1)
     n
     (* n
        (factorial (dec n)))))

 (factorial 5)
 (map factorial (range 0 10))

 (factorial 1000)
 (/ (factorial 1000) (factorial 999))

 (map (fn [n] (* n n))
      (range 1 10))


 ;; Maps are used to represent almost everything
 ;; JSON
 (require '[cheshire.core :as json])

 (json/decode (slurp "data.json"))
 (spit "richo.json" (json/encode richo {:pretty true}))

 ;; HTTP request/response
 (require '[ring.adapter.jetty :as jetty])

 (defn handler [request]
   (clojure.pprint/pprint request)
   (dotimes [_ 4] (println))
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body "Hello world"})

 (def server (jetty/run-jetty #'handler
                              {:port 3000, :join? false}))

 (.stop server)

 (require '[ring.middleware.params :refer [wrap-params]])

 (def server (jetty/run-jetty (wrap-params #'handler)
                              {:port 3000, :join? false}))


 ;; HTML
 (require '[hiccup.core :refer :all])
 (require '[hiccup.page :as page])

 (html [:div#foo.bar "Richo capo"])

 (page/html5
  [:p [:div#counter 0]]
  [:p [:button#btn {:type "button"} "Press me!"]]
  [:script "btn.onclick = function () { counter.innerText++; }"])

 (defn handler [request]
   {:status 200
    :headers {"Content-Type" "text/html"}
    :body (page/html5
           [:p [:div#counter 0]]
           [:p [:button#btn {:type "button"} "Press me!"]]
           [:script "btn.onclick = function () { counter.innerText++; }"])})

 ;; Database rows


 ;; Parser
 ;; Config

 ;; What about mutable state?
 ;; Atoms (@, swap!, reset!, ...)
 ;; Atoms are thread-safe (data race example C# vs clj)

 ;; What about polymorphism?
 ;; Geometric shapes example
 ;; Multimethods
 ;; Protocols/Types/Records

 ;; Future demos (macros? clojure.spec? core.async?)
 ,)
