(ns clojure-demo.demo11-atoms)

(comment

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

 ,)
