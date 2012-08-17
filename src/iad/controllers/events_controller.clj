(ns iad.controllers.events-controller
  (:require [iad.model.event :as event]))

(defn all-events []
  (event/all))
