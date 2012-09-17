(require '[iad.core :as iad])
(require '[iad.db :as db])
(require '[clj-http.client :as client])
(require '[clj-json.core :as json])
(require '[iad.model.event :as event])
(require '[iad.seeddata.sample :as sample])
(use '[clojure.tools.logging :only [info warn error spy]])

(def events (atom []))
(def iad-server (atom (iad/start)))
(System/setProperty "IAD_ENV", "test")

(Before [] (db/migrate))

(defn assert-count [expected actual]
  (let [msg (str expected " expected and got " actual " instead")]
    (assert (= (str actual) (str expected)) msg)))

(Given #"^the conference is still far away in the future$" [])

(When #"^I request the list of the events$" []
  (let [raw-json-events ((client/get "http://localhost:8888/events") :body )
        json-events (json/parse-string raw-json-events)
        event-count (count json-events)]
    (if-not (= 0 event-count) (swap! events #(apply conj % json-events)))))

(Then #"^the list of events is empty$" []
  (let [howmany (count @events)]
    (assert-count 0 howmany)))
;
;(Given #"^the keynote was confirmed$" []
;       (event/create {:name "agile development with clojure"
;                      :from_date "2012-11-24 09:30:00"
;                      :to "2012-11-24 11:30:00"
;                      :location "ballroom"
;                      :description "why clojure should be agile?"}))

(Given #"^the keynote was confirmed$" []
  (sample/simple-event))

(Then #"^there is \"([^\"]*)\" event$" [expected-events]
  (let [howmany (count @events)]
    (assert-count expected-events howmany)))

(After [] (db/drop-tables))
