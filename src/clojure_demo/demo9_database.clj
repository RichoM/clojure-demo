(ns clojure-demo.demo9-database
  (:require [clojure.pprint :refer [pprint]]
            [clojure.string :as str]
            [conman.core :refer [connect! disconnect! bind-connection] :as conn]
            [cheshire.core :as json]))

(comment

 ;; Database
 (def ^:dynamic *db* (connect! {:jdbc-url "jdbc:mysql://localhost:3306/pokedex?useSSL=false&user=guest&password=turingturing"}))
 (bind-connection *db* "clojure_demo/queries.sql")

 (take 9 (get-all-pokemons))
 (get-all-types)

 (def pokedex (json/decode (slurp "pokedex.json")))

 (def types (set (mapcat #(get % "type") pokedex)))


 ; Populate type table
 (doseq [type types]
   (insert-type! {:name type}))

 ; Populate pokemon table
 (conn/with-transaction [*db*]
   (doseq [pokemon (map (fn [pokemon]
                          (into {:id (pokemon "id"),
                                 :name (get-in pokemon ["name" "english"])}
                                (map (fn [[key val]]
                                       [(-> (str key)
                                            str/lower-case
                                            (str/replace ". " "_")
                                            keyword)
                                        val])
                                     (pokemon "base"))))
                        pokedex)]
     (insert-pokemon! pokemon)))

 ; Populate pokemon_type table
 (conn/with-transaction [*db*]
   (doseq [{id "id", types "type"} (take 5 pokedex)]
     (doseq [type types]
       (try (add-pokemon-type! {:id id, :type type})
         (catch Throwable _)))))



 (disconnect! *db*)



 ,)
