(ns iad.model.event
  (:require [iad.db :as db])
  (:use [clojure.tools.logging :only (spy)])
  (:require [clojure.java.jdbc :as sql]))

(defn date-to-string [a-map a-symbol]
  (let [to-string (fn [value] (. value toString))
        swap (fn [a-map sym] (assoc a-map sym (to-string (a-map sym))))]
  (swap a-map a-symbol)))
  
(defn to-date-to-string [coll]
  (map #(date-to-string % :to) coll))
  
(defn from-date-to-string [coll]
  (map #(date-to-string % :from_date) coll))
  
(defn all-dates-to-string [coll]
  (to-date-to-string (from-date-to-string coll)))

(defn all []
  (sql/with-connection (db/iad)
    (sql/with-query-results rs ["select * from event"] (all-dates-to-string (doall rs)))))

(defn create [event]
  (sql/with-connection (db/iad) 
    (sql/insert-records :event event)))

(defn single [eventid]
  (sql/with-connection (db/iad)
    (sql/with-query-results rs ["select * from event where id=?" eventid]
      (all-dates-to-string (doall rs)))))
