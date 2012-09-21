(require '[iad.core :as iad])
(require '[clj-http.client :as client])
(require '[clj-json.core :as json])
(require '[iad.model.presentation :as presentation])
(use '[clojure.tools.logging :only [info warn error spy]])

(System/setProperty "IAD_ENV", "test")
(def presentations-atom (atom []))
(def iad-server (atom (iad/start)))

(defn fetch-events-by-name
  "Fetch the list of events starting with a given name from the IAD server"
  [event-name]
  (let [url (str "http://localhost:8888/events?startingWithName=" event-name)
        raw-json-events ((client/get url) :body)]
    (json/parse-string raw-json-events)))

(defn fetch-presentations-by-event
  "Fetch all the presentations for the given event map"
  [event]
  (let [url (str "http://localhost:8888/events/" (:id event) "/presentations")
        raw-json-presentations ((client/get url) :body)]
    (json/parse-string raw-json-presentations)))

(Given #"^the \"([^\"]*)\" event schedule was finalized$" [event-name]
       (seed/event event-name))

(Given #"^the talk \"([^\"]*)\" was confirmed for \"([^\"]*)\"$" [presentation-title event-name]
       (seed/presentation presentation-title event-name))

(When #"^I request the list of presentations for \"([^\"]*)\"$" [event-name]
  (let [event (first (fetch-events-by-name event-name))
        presentations (fetch-presentations-by-event event)
        presentations-count (count presentations)]
    (if-not (= 0 presentations-count) (swap! presentations-atom #(apply conj % presentations)))))

(Then #"^there are \"([^\"]*)\" presentations$" [presentation-count]
      (let [howmany (count @presentations-atom)]
        (assert-count expected-presentations howmany)))
