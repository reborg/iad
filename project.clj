(defproject iad/iad "0.1" 
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.1"]
                 [ring-json-params "0.1.3"]
                 [clj-json "0.5.1"]
                 [compojure "1.1.1"]]
  :profiles {:dev {:dependencies [[midje "1.4.0"]]}}
  :main iad.core
  :min-lein-version "2.0.0"
  :plugins [[lein-midje "1.0.10"]
            [lein-cucumber "1.0.0"]]
  :cucumber-feature-paths ["test/features/"]
  :description "IAD Conference Rest Server")
