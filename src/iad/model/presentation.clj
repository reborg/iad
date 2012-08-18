(ns iad.model.presentation)

(defn all [eventid]
  (sql/with-connection (db/iad)
    (sql/with-query-results rs ["select * from presentation where eventid=?" eventid] )))

