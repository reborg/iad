(ns iad.controllers.events-controller
  (:require [iad.model.event :as event])
  (:require [iad.model.presentation :as presentation]))

(defn all-events []
  (event/all))

(defn single-event [eventid]
  (event/single eventid))

(defn all-event-presentations [eventid]
  (presentation/all eventid))

(defn single-presentation [presentationid]
  (presentation/single presentationid))