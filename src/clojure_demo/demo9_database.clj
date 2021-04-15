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

 (def pokedex (json/decode (slurp "pokedex.json") true))

 (def types (set (mapcat :types pokedex)))

 (conn/with-transaction [*db*]
   (doseq [type types]
     (insert-type! {:name type}))
   (doseq [pokemon pokedex]
     (insert-pokemon! pokemon)
     (doseq [type (:types pokemon)]
       (add-pokemon-type! {:id (:id pokemon), :type type}))))

 (get-pokemon-by-id {:id 185})
 (get-pokemon-types (get-pokemon-by-id {:id 185}))

 (disconnect! *db*)

 ,)
