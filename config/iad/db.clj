(ns iad.db
  (:use [clojure.tools.logging :only (warn error)])
  (:require [clojure.java.jdbc :as sql]) 
  (:use [iad.config.environment]))

(def iad-db-env {"dev" {:classname   "org.h2.Driver"
                        :subprotocol "h2:mem"
                        :subname     "dev;DB_CLOSE_DELAY=-1"
                        :user        "sa"
                        :password    ""}
                 "test" {:classname   "org.h2.Driver"
                         :subprotocol "h2:mem"
                         :subname     "test;DB_CLOSE_DELAY=-1"
                         :user        "sa"
                         :password    ""}
                 "prod" {:classname   "org.h2.Driver"
                         :subprotocol "h2:mem"
                         :subname     "prod;DB_CLOSE_DELAY=-1"
                         :user        "sa"
                         :password    ""}})

(def event-schema [:event 
                   [:id "identity"]
                   [:title "varchar(255)"]
                   [:author "varchar(90)"]
                   [:schedule "timestamp"]
                   [:length "int"]
                   [:active "boolean"]
                   [:abstract "varchar(600)"]])

;           (sql/insert-records :event
;              {:title "agile development with clojure" 
;               :author "ramborg" 
;               :schedule "2012-11-24 09:30:00"
;               :length "45"
;               :active true
;               :abstract "why clojure should be agile?"}))))

(defn migrate []
  (sql/with-connection (iad-db-env (. System getProperty "IAD_ENV"))
    (apply sql/create-table event-schema)))
