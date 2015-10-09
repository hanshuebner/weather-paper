(defproject weather-paper "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [cheshire "5.5.0"]
                 [clj-http "2.0.0"]
                 [tikkba "0.5.0" :exclusions [org.clojure/clojure]]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler weather-paper.handler/app
         :init weather-paper.handler/init
         :destroy weather-paper.handler/destroy}
  :profiles {:uberjar {:aot :all}
             :production {:ring {:open-browser? false
                                 :stacktraces? false
                                 :auto-reload? false}}
             :dev {:dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.3.1"]]}})
