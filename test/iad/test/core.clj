(ns iad.test.core
  (:use [iad.test-helper])
  (:use [iad.core])
  (:use [midje.sweet]))

(background (before :facts (test-setup)))

(facts "rest functions"

       (fact "the response is json type"
             (json-response {"response" "{'myjson': 'this is json'}"}) => 
               {:status 200, 
                :headers {"Content-Type" "application/json"}, 
                :body "{\"response\":\"{'myjson': 'this is json'}\"}"})

       (fact "the error response is reported"
             (json-response {"retry later" "403"} ) => 
               {:status 200, 
                :headers {"Content-Type" "application/json"}, 
                :body "{\"retry later\":\"403\"}"}))
