(defproject clojure-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [cheshire "5.9.0"] ; JSON
                 [compojure "1.6.2"] ; routing
                 [ring/ring-core "1.7.1"] ; web-framework
                 [ring/ring-jetty-adapter "1.7.1"] ; jetty webserver
                 [hiccup "1.0.5"] ; html generator
                 [conman "0.8.3"] ; database connection
                 [com.layerware/hugsql "0.5.1"] ; sql data-access
                 [mysql/mysql-connector-java "8.0.12"]
                 [clj-petitparser "0.1.0-SNAPSHOT"]]
  :main ^:skip-aot clojure-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:dependencies [[proto-repl "0.3.1"]
                                  [org.clojure/tools.cli "1.0.194"]
                                  [org.clojure/tools.namespace "0.3.1"]
                                  [org.clojars.beppu/clj-audio "0.3.0"]
                                  [org.clojure/test.check "0.9.0"]]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]
                   :source-paths ["env/dev/clj"]
                   :repl-options {:init-ns user}}
             :test {:resource-paths ["env/test/sounds"]
                    :source-paths ["env/test/clj"]}})
