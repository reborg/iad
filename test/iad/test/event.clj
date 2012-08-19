(ns iad.test.event
  (:use [midje.sweet])
  (:use [iad.model.event]))

(facts "rs parsing"
  (fact "the empty list is a valid json"
  (schedule-to-string coll) => expectedColl)
  )
