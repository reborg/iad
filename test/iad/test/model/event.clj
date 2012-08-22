(ns iad.test.model.event
  (:use [iad.test-helper])
  (:require [iad.model.event :as event])
  (:require [iad.db :as db])
  (:use [midje.sweet]))

(against-background [(before :contents (db/migrate))]) 

(facts "creating new events"
       (fact "it creates a new event" (count (event/all)) => 1)
       (fact "an id is automatically assigned" (:id (first (event/all))) => 1)
       (against-background 
         (before :checks 
                 (event/create {:name "agile development with clojure" 
                                :from_date "2012-11-24 09:30:00"
                                :to "2012-11-24 11:30:00"
                                :location "ballroom"
                                :description "why clojure should be agile?"}))))
