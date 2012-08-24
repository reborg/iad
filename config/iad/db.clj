(ns iad.db
  (:require [clojure.java.jdbc :as sql]) 
  (:use [iad.config.environment]))

(defn iad [] 
  (let [iad-env (. System getProperty "IAD_ENV")]
    (if (or (= nil iad-env) (= "" iad-env))
      dev-db 
      ({"dev" dev-db
        "test" test-db
        "prod" prod-db} iad-env))))

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

(defn migrate []
  (sql/with-connection (iad)
    (apply sql/create-table event-schema)
    (apply sql/create-table presentation-schema)))

(defn drop-tables []
  (sql/with-connection (iad)
    (try
      (sql/drop-table :event)
      (sql/drop-table :presentation)
     (catch Exception _))))
