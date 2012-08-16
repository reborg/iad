(ns iad.controllers.events-controller)
(require '[clojure.java.jdbc :as sql]) 

(defn all-events []
  (let [iad-test {:classname   "org.h2.Driver"
                  :subprotocol "h2:mem"
                  :subname     "test"
                  :user        "sa"
                  :password    ""}]
    (sql/with-connection iad-test 
      (sql/with-query-results rs ["select * from event"]
        rs))))
