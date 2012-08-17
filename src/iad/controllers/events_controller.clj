(ns iad.controllers.events-controller
  (:use [clojure.tools.logging :only (info error)])
  (:require [iad.db :as db])
  (:require [clojure.java.jdbc :as sql]))

(defn all-events []
  (sql/with-connection (db/iad-db-env (. System getProperty "IAD_ENV"))
    (sql/with-query-results rs ["select * from event"] 
      (map #(assoc % :schedule (. (% :schedule) toString)) (doall rs)))))
