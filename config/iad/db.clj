(ns iad.db
  (:use [clojure.tools.logging :only (warn error)])
  (:require [clojure.java.jdbc :as sql]) 
  (:use [iad.config.environment]))

(defn iad [] ({"dev" {:classname   "org.h2.Driver"
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
                         :password    ""}} (. System getProperty "IAD_ENV")))


;todo: should be "from" not from_date but from is a reserved word in sql
(def event-schema [:event 
                   [:id "identity"]
                   [:name "varchar(50)"]
                   [:description "varchar(255)"]
                   [:from_date "timestamp"]
                   [:to "timestamp"]
                   [:location "varchar(90)"]
                   ])


(def presentation-schema [:presentation
                   [:id "identity"]
                   [:title "varchar(255)"]
                   [:speaker "varchar(90)"]
                   [:eventid "int"]
                   [:active "boolean"]
                   [:summary "varchar(600)"]
                   [:track "varchar(255)"]
                   [:room "varchar(255)"]
                   [:experience "varchar(90)"]
                   [:language "varchar(2)"]
                   ])


;           (sql/insert-records :event
;              {:title "agile development with clojure" 
;               :author "ramborg" 
;               :schedule "2012-11-24 09:30:00"
;               :length "45"
;               :active true
;               :abstract "why clojure should be agile?"}))))

(defn migrate []
  (sql/with-connection (iad)
    (apply sql/create-table event-schema)
    (apply sql/create-table presentation-schema)))
