(ns clojure-demo.demo12-concurrency)

(comment

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

 ,)
