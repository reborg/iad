(ns iad.core
  (:use ring.adapter.jetty
        ring.middleware.reload
        ring.middleware.stacktrace
        ring.util.response
        compojure.core)
  (:require [compojure.route :as route]))

(defn index "Show all events" [req]
  (response (str "Look ma no wheels")))

(defroutes app
    (GET "/" [] "<h1>Hello World</h1>")
    (route/not-found "<h1>Page not found</h1>"))

(defn -main [& port]
  (run-jetty #'app {:port (or port 8888) :join? false}))
