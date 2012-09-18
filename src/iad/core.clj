(ns iad.core
  (:use ring.adapter.jetty
        ring.middleware.reload
        ring.middleware.stacktrace
        ring.util.response
        compojure.core
        iad.controllers.events-controller)
  (:use [clojure.tools.logging :only (info error)])
  (:require [compojure.route :as route])
  (:require [iad.db :as db])
  (:require [iad.seed :as seed])
  (:require [clj-json.core :as json]))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes handler
    (GET "/events" [] (json-response (all-events)))
    (GET "/events/:eventid" [eventid] (json-response (single-event eventid)))
    (GET "/events/:eventid/presentations" [eventid] (str "<html><body><h1>Hello user " eventid "</h1></body></html>"))
    (route/not-found (json-response {"response" "endpoint not available"})))

(defn start [& port]
  (defonce server (run-jetty #'handler {:port (or port 8888) :join? false})))

(defn stop []
  (.stop server))

(defn restart []
  (.stop server) 
  (.start server))

(defn get-env []
  ((fnil identity "dev") (. System getProperty "IAD_ENV")))

(defn -main [& port]
  (do
    (info (str "Starting IAD server in " (get-env) " environment"))
    (db/drop-tables)
    (db/migrate)
    (seed/all)
    (run-jetty #'handler {:port (or port 8888) :join? false})))
