(ns iad.seed
  (:require [iad.model.event :as event]))

(defn events []
  (do
    (event/create {:name "IAD-2012" 
                   :from_date "2012-11-24 09:30:00"
                   :to "2012-11-24 18:30:00"
                   :location "Milano"
                   :description "The Italian Agile Day"})
    (event/create {:name "IAD-Venice-2012" 
                   :from_date "2012-11-22 09:30:00"
                   :to "2012-11-22 18:30:00"
                   :location "Venice"
                   :description "The Italian Agile Day in Venice"})))

(defn all []
  (do
    (events)))
