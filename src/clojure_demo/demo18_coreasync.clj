(ns clojure-demo.demo18-coreasync
  (:require [clojure.core.async :as async :refer :all])
  (:import [java.util Date]))

(comment

 ;; Multithreading
 ;; Channels
 ;; Asynchronous programming

 (thread (dotimes [_ 5]
                  (Thread/sleep 1000)
                  (println (System/currentTimeMillis))))

 (go (dotimes [_ 5]
              (<! (timeout 1000))
              (println (System/currentTimeMillis))))

 (def c (chan))

 (put! c "Hola")
 (put! c "mundo")
 (put! c "!")

 (take! c println)

 (<!! c)

 ;; TODO(Richo)
 ,)
