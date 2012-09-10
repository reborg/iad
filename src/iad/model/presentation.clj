(ns iad.model.presentation
  (:require [iad.db :as db])
  (:require [clojure.java.jdbc :as sql]))

(defn all [eventid]
  (sql/with-connection (db/iad)
    (sql/with-query-results rs ["select * from presentation where eventid=?" eventid] )))
