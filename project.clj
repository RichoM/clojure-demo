(defproject clojure-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.async "0.6.532"]
                 [cheshire "5.9.0"] ; JSON
                 [compojure "1.6.2"] ; routing
                 [ring/ring-core "1.7.1"] ; web-framework
                 [ring/ring-json "0.5.1"] ; JSON middleware
                 [ring/ring-jetty-adapter "1.7.1"] ; jetty webserver
                 [hiccup "1.0.5"] ; html generator
                 [conman "0.8.3"] ; database connection
                 [com.layerware/hugsql "0.5.1"] ; sql data-access
                 [mysql/mysql-connector-java "8.0.12"]
                 [clj-petitparser "0.1.1"]]
  :main ^:skip-aot clojure-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:dependencies [[proto-repl "0.3.1"]
                                  [org.clojure/tools.cli "1.0.194"]
                                  [org.clojure/tools.namespace "0.3.1"]
                                  [org.clojars.beppu/clj-audio "0.3.0"]
                                  [org.clojure/test.check "0.9.0"]
                                  [criterium "0.4.6"]]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]
                   :source-paths ["env/dev/clj"]
                   :repl-options {:init-ns user}
                   :global-vars {*print-length* 100}}
             :test {:resource-paths ["env/test/sounds"]
                    :source-paths ["env/test/clj"]}})
