(ns iad.test.event
  (:use [midje.sweet])
  (:use [iad.model.event]))

;todo: how to test schedule-to-string?
;(facts "rs parsing"
;  (fact "the empty list is a valid json"
;    (let [expectedColl {"a" 1 "b" 2}]
;      (schedule-to-string {"a" 1 "b" 2}) => expectedColl)
;    ))
