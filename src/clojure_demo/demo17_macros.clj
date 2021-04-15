(ns clojure-demo.demo17-macros)

(comment

 ;; Macros are essential
 ;; Examples: and, or, ->, dotimes, time, comment, with-open

 (macroexpand '(and 1 2))
 (macroexpand '(dotimes [i 5]
                        (println i)))

 (defmacro backwards
   [form]
   (reverse form))

 (defmacro infix
   "Use this macro when you pine for the notation of your childhood"
   [infixed]
   (list (second infixed) (first infixed) (last infixed)))

 (backwards (3 4 +))

 (macroexpand '(-> "richo"
                   str/upper-case
                   (str "!")
                   println))

 (fn [a] (* a a))
 #(* % %)

 (macroexpand '#(* % %))



 ,)
