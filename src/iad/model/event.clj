(ns iad.model.event
  (:require [iad.db :as db])
  (:require [clojure.java.jdbc :as sql]))

(defn schedule-to-string [coll]
  (map #(assoc % :to (. (% :to) toString)) coll))
  
(defn all []
  (sql/with-connection (db/iad)
    (sql/with-query-results rs ["select * from event"] (println (doall rs)))))

;(defn all []
;  (sql/with-connection (db/iad)
;    (sql/with-query-results rs ["select * from event"] (schedule-to-string (doall rs)))))

(defn single [eventid]
  (sql/with-connection (db/iad)
    (sql/with-query-results rs ["select * from event where id=?" eventid] (schedule-to-string (doall rs)))))
