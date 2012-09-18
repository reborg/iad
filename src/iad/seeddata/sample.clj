(ns iad.seeddata.sample

  (:require [iad.model.event :as event])
  (:require [iad.model.presentation :as presentation]))

(defn simple-event []
  (do
    (event/create {:name "IAD 2012"
                   :from_date "2012-11-24 09:30:00"
                   :to "2012-11-24 18:30:00"
                   :location "Milano"
                   :description "The Italian Agile Day 2012"})
    (event/create {:name "IAD 2011"
                   :from_date "2011-11-24 09:30:00"
                   :to "2011-11-24 18:30:00"
                   :location "Roma"
                   :description "The Italian Agile Day 2011"})
    (presentation/create {:room "big room"
                          :eventid 1
                          :speaker "Uberto Barbini"
                          :title "Clojure rest server"
                          :summary "Blablabla"}))
  )
