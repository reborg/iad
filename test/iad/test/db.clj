(ns iad.test.db
  (:use [iad.test-helper])
  (:use [iad.db])
  (:use [midje.sweet]))

(against-background [] (System/setProperty "IAD_ENV", ""))

(facts "db config"
       (fact "dev is default when no environment set"
             (:subname (iad)) => #"dev"))
