(ns iad.test.model.event
  (:use [iad.test-helper])
  (:require [iad.model.event :as event])
  (:require [iad.db :as db])
  (:use [midje.sweet]))

(against-background [(before :contents (db/drop-tables))]) 
(against-background [(before :contents (db/migrate))]) 

(facts "creating new events"
       (fact "it creates a new event" (count (event/all)) => 1)
       (fact "an id is automatically assigned" (:id (first (event/all))) => 1)
       (against-background 
         (before :checks 
                 (event/create {:name "IAD" 
                                :from_date "2012-11-24 09:30:00"
                                :to "2012-11-24 18:30:00"
                                :location "Milano"
                                :description "The Italian Agile Day"}))))

(facts "my json lib don't know how to map a java util date, so I do my own"
       (fact "replace util dates for all maps in a collection"
             (let [now (java.util.Date.)
                   now-as-string (. now toString)]
               (first (event/to-date-to-string [{:a 1 :to now}])) => {:a 1 :to now-as-string}))
       (fact "replace a specific util date in a map"
             (let [now (java.util.Date.)
                   now-as-string (. now toString)]
               (event/date-to-string {:a 1 :from_date now} :from_date) => {:a 1 :from_date now-as-string}))
       (fact "replace all of the keys representing dates"
             (let [now (java.util.Date.)
                   now-as-string (. now toString)]
               (first (event/all-dates-to-string [{:a 1 :from_date now :to now}])) => {:a 1 :from_date now-as-string :to now-as-string})))

