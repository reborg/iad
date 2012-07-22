(ns iad.test.core
  (:use [iad.core])
  (:use [midje.sweet]))

(facts "there are facts"
       (fact "some of them are true"
             (= "" "") => truthy))
