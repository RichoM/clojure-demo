(defproject clojure-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main ^:skip-aot clojure-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:dependencies [[proto-repl "0.3.1"]
                                  [org.clojure/tools.cli "1.0.194"]
                                  [org.clojure/tools.namespace "0.3.1"]
                                  [org.clojure/core.async "0.6.532"]
                                  [org.clojars.beppu/clj-audio "0.3.0"]]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]
                   :global-vars {*unchecked-math* :warn-on-boxed
                                 *warn-on-reflection* true}
                   :source-paths ["env/dev/clj"]
                   :repl-options {:init-ns user}}
             :test {:resource-paths ["env/test/sounds"]
                    :source-paths ["env/test/clj"]}})
