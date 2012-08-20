(ns iad.db
  (:use [clojure.tools.logging :only (warn error)])
  (:require [clojure.java.jdbc :as sql]) 
  (:use [iad.config.environment]))

(defn iad [] 
  (let [iad-env (. System getProperty "IAD_ENV")]
    (if (or (= nil iad-env) (= "" iad-env))
      dev-db 
      ({"dev" dev-db
        "test" test-db
        "prod" prod-db} iad-env))))

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
  (sql/with-connection (iad)
    (apply sql/create-table event-schema)))
