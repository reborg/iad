(ns iad.test.model.presentation
  (:use [iad.test-helper])
  (:require [iad.model.presentation :as presentation])
  (:require [iad.db :as db])
  (:use [midje.sweet]))

(against-background [(before :contents (db/drop-tables))])
(against-background [(before :contents (db/migrate))])

(facts "creating new presentation"
  (fact "it creates a new presentation" (count (presentation/all 101)) => 1)
  (fact "an id is automatically assigned" (:id (first (presentation/all 101))) => 1)
  (fact "it gets only presentation for an event" (count (presentation/all 1)) => 0)
  (against-background
    (before :checks (presentation/create {:title "title"
                                          :speaker "speaker"
                                          :eventid 101
                                          :active true
                                          :summary "summary"
                                          :track "track"
                                          :room "room"
                                          :experience "exp"
                                          :language "it"
                                          }))))

