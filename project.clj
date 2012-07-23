(defproject iad "0.1"
  :description "IAD Conference Rest Server"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.1"]
                 [ring-json-params "0.1.3"]
                 [clj-json "0.5.1"]
                 [compojure "1.1.1"]]
                 
  :dev-dependencies [[lein-midje "1.0.10"]
                     [midje "1.4.0"]]
  :main iad.core)
