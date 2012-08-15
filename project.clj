(defproject iad/iad "0.1" 
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.1"]
                 [ring-json-params "0.1.3"]
                 [clj-json "0.5.1"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [com.h2database/h2 "1.3.168"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [org.clojure/tools.logging "0.2.3"]
                 [compojure "1.1.1"]]
  :profiles {:dev {:dependencies [[midje "1.4.0"]
                                  [clj-http "0.5.0"]]}}
  :main iad.core
  :source-paths ["src","config"]
  :min-lein-version "2.0.0"
  :plugins [[lein-midje "2.0.0-SNAPSHOT"]
            [lein-cucumber "1.0.0"]]
  :cucumber-feature-paths ["test/features/"]
  :repositories {"ibiblio" "http://mirrors.ibiblio.org/pub/mirrors/maven2/"
                "clojars.org" "http://clojars.org/repo"}
  :description "IAD Conference Rest Server")
