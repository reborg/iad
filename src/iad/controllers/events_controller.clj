(ns iad.controllers.events-controller
  (:require [iad.model.event :as event]))

(defn all-events []
  (event/all))

(defn single-event [eventid]
  (event/single eventid))

(defn all-event-presentations [eventid]
  (event/all))