(ns iad.controllers.events-controller)
(require '[clojure.java.jdbc :as sql]) 

(defn all-events []
  (let [iad-test {:classname   "org.h2.Driver"
                  :subprotocol "h2:file"
                  :subname     (str (System/getProperty "user.dir") "/db/" "iad-test")
                  :user        "sa"
                  :password    ""}]
    (sql/with-connection iad-test 
      (sql/with-query-results rs ["select * from event"]
        (into [] rs)))))
