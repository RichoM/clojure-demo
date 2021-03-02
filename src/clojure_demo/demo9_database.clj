(ns clojure-demo.demo9-database
  (:require [conman.core :refer [connect! disconnect! bind-connection]]))

(comment

 ;; Database
 (def db (conman/connect! {:jdbc-url "jdbc:mysql://localhost:3306/clojure_demo?useSSL=false&user=guest&password=turingturing"}))
 (conman/bind-connection db "clojure_demo/queries.sql")

 (select-all-people)
 (insert-person {:nombre "Gonzalo", :apellido "Zabala" :edad 40})
 (update-person {:id 1, :nombre "Ricardo", :apellido "Moran", :edad 32})

 (conman/disconnect! db)

 ,)
