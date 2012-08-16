(ns iad.test.json-parsing
  (:use [iad.core])
  (:use [midje.sweet]))

(require '[clj-json.core :as json])

(facts "json parsing"
  (fact "the empty list is a valid json"
    (json/parse-string "[]") => [])


  (fact "the 3 elements list"
    (json/parse-string "[1,2,3]") => [1,2,3])

  (fact "single event json"
    (json/parse-string "[{\"id\":1, \"from\":\"2012-11-26\", \"to\": \"2012-11-26\",
\"name\":\"Italian Agile Day 2012\"}]") => (just [{"id" 1, "from" "2012-11-26", "to" "2012-11-26", "name" "Italian Agile Day 2012"}])
  )

)
