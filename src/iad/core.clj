(ns iad.core
  (:use ring.adapter.jetty
        ring.middleware.reload
        ring.middleware.stacktrace
        ring.util.response
        compojure.core
        iad.controllers.events-controller)
  (:require [compojure.route :as route])
  (:require [clj-json.core :as json]))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes handler
    (GET "/" [] (json-response (all-events)))
    (route/not-found (json-response {"endpoint" "not available"})))

(defn start [& port]
  (defonce server (run-jetty #'handler {:port (or port 8888) :join? false})))

(defn stop []
  (.stop server))

(defn restart []
  (.stop server) 
  (.start server))

(defn -main [& port]
  (run-jetty #'handler {:port (or port 8888) :join? false}))
