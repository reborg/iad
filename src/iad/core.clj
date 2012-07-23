(ns iad.core
  (:use ring.adapter.jetty
        ring.middleware.reload
        ring.middleware.stacktrace
        ring.util.response
        compojure.core)
  (:require [compojure.route :as route])
  (:require [clj-json.core :as json]))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes handler
    (GET "/" [] (json-response {"hello" "world"}))
    (PUT "/" [name] (json-response {"hello" name}))
    (route/not-found "<h1>Page not found</h1>"))

(defn -main [& port]
  (run-jetty #'handler {:port (or port 8888) :join? false}))
