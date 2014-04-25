(defproject web "0.1.0-SNAPSHOT"
  :source-paths ["src/clj" "src/cljs"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [ring "1.2.1"]
                 [compojure "1.1.6"]
                 [enlive "1.1.5"]
                 [hiccup "1.0.5"]
                 [enfocus "2.0.2"]]
  :ring {:handler web.handler/app}
  :profiles {:dev {:repl-options {:init-ns web.handler}
                   :plugins [[com.cemerick/austin "0.1.3"]
                             [lein-cljsbuild "1.0.1"]
                             [lein-ring "0.8.10"]]
                   :dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]
                                  [lein-ring "0.8.10"]]
                   :cljsbuild {:builds [{:source-paths ["src/cljs"]
                                         :compiler {:output-to "target/classes/public/app.js"
                                                    :optimizations :simple
                                                    :pretty-print true}}]}}})
