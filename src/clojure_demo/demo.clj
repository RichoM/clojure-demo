(ns clojure-demo.demo
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]))

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
 (html [:img {:src "imagen.jpg"}])

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

 ;; Database
 (require '[conman.core :as conman])

 (def db (conman/connect! {:jdbc-url "jdbc:mysql://localhost:3306/clojure_demo?useSSL=false&user=guest&password=turingturing"}))
 (conman/bind-connection db "clojure_demo/queries.sql")

 (select-all-people)
 (insert-person {:nombre "Gonzalo", :apellido "Zabala" :edad 40})
 (update-person {:id 1, :nombre "Ricardo", :apellido "Moran", :edad 32})

 (conman/disconnect! db)

 ;; Parser
 (require '[petitparser.core :as pp])

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

 ;; What about mutable state?
 ;; Atoms (@, swap!, reset!, ...)

 (def counter (atom 0))

 counter

 @counter

 (swap! counter inc)

 (reset! counter 0)



 (def game-state
   (atom {:player {:name "Ash", :level 1,
                   :party ["Pikachu" "Pidgey" "Caterpie"]}
          :rival {:name "Gary", :level 10,
                  :party ["Raichu" "Pidgeot" "Butterfree"]}}))

 @game-state

 (swap! game-state update-in [:player :level] inc)
 (get-in @game-state [:player :level])

 (swap! game-state assoc-in [:player :name] "Red")
 (get-in @game-state [:player :name])

 (swap! game-state update-in [:player :party] conj "Charmander")
 (get-in @game-state [:player :party])


 (swap! game-state
        #(-> %
             (update-in [:player :level] inc)
             (assoc-in [:player :name] "Blue")
             (update-in [:player :party] conj "Bulbasaur")))

 (macroexpand '(-> "richo"
                   str/upper-case
                   (str "!")
                   println))

 (fn [a] (* a a))
 #(* % %)

 (macroexpand '#(* % %))

 ;; Atoms are thread-safe (data race example)
 (def nthreads 10)
 (def niter 500000)
 (def counter (atom 0))

 (defn create-thread []
   (Thread. #(dotimes [_ niter]
                      (swap! counter inc))))

 (defn run-threads []
   (reset! counter 0)
   (let [threads (vec (repeatedly nthreads create-thread))]
     (doseq [^java.lang.Thread t threads]
       (.start t))
     (doseq [^java.lang.Thread t threads]
       (.join t)))
   (let [expected (* nthreads niter)]
     (println "ACTUAL:" @counter ", EXPECTED:" expected)
     (println "RESULT:" (if (= @counter expected)
                          "SUCCESS"
                          "ERROR"))))

 (run-threads)


 ;; What about polymorphism?
 ;; Geometric shapes example

 (defn make-circle [r] {:type :circle, :radius r})
 (defn make-triangle [b h] {:type :triangle, :base b, :height h})
 (defn make-square [s] {:type :square, :side s})
 (defn make-rectangle [l w] {:type :rectangle, :length l, :width w})

 (defn area [shape]
   (case (:type shape)
     :circle (* Math/PI
                (:radius shape)
                (:radius shape))
     :triangle (* 1/2
                  (:base shape)
                  (:height shape))
     :square (* (:side shape)
                (:side shape))
     :rectangle (* (:length shape)
                   (:width shape))
     (throw (Exception. "Unknown shape"))))

 (area (make-rectangle 3 5))


 (defmulti area :type)

 (defmethod area :circle [shape]
   (* Math/PI
      (:radius shape)
      (:radius shape)))

 (defmethod area :triangle [shape]
   (* 1/2
      (:base shape)
      (:height shape)))

 (defmethod area :square [shape]
   (* (:side shape)
      (:side shape)))

 (defmethod area :rectangle [shape]
   (* (:length shape)
      (:width shape)))

 (defmethod area :default [shape]
   (throw (Exception. "Unknown shape")))

 (area nil)

 ;; Multimethods are flexible

 (defmulti encounter (fn [x y] [(:species x) (:species y)]))

 (defmethod encounter [:bunny :lion] [b l] :run-away)
 (defmethod encounter [:lion :bunny] [l b] :eat)
 (defmethod encounter [:lion :lion] [l1 l2] :fight)
 (defmethod encounter [:bunny :bunny] [b1 b2] :mate)

 (def bugs {:species :bunny, :name "Bugs Bunny"})
 (def lola {:species :bunny, :name "Lola Bunny"})
 (def mufasa {:species :lion, :name "Mufasa"})
 (def scar {:species :lion, :name "Scar"})

 (encounter bugs mufasa)
 (encounter scar bugs)
 (encounter mufasa scar)
 (encounter bugs lola)

 ;; Protocols/Types/Records
 ;; Future demos (macros? clojure.spec? core.async?)
 ,)
