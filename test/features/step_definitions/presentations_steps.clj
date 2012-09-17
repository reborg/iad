(require '[iad.core :as iad])
(require '[iad.db :as db])
(require '[clj-http.client :as client])
(require '[clj-json.core :as json])
(require '[iad.model.presentation :as presentation])
(use '[clojure.tools.logging :only [info warn error spy]])

(def presentations (atom []))
(def iad-server (atom (iad/start)))
(System/setProperty "IAD_ENV", "test")

(Before [] (db/migrate))


(When #"^I request the list of presentations of the event \"([^\"]*)\" $" [eventid]
(comment  Express the Regexp above with the code you wish you had  )
  (let [raw-json-presentations ((client/get "http://localhost:8888/events/1/presentations") :body)
        json-presentations (json/parse-string raw-json-presentations)
        presentations-count (count json-presentations)]
    (if-not (= 0 presentations-count) (swap! presentations #(apply conj % json-presentations)))))


(Then #"^there is \"([^\"]*)\" presentation$" [expected-presentations]
  (let [howmany (count @presentations)]
    (assert-count expected-presentations howmany)))

(comment  Express the Regexp above with the code you wish you had  )
