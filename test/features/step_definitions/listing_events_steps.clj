(require '[iad.core :as iad])
(require '[iad.db :as db])
(require '[clj-http.client :as client])
(require '[clj-json.core :as json])
(use '[clojure.tools.logging :only [info warn error]])

(Before [] 
        (do
          (System/setProperty "IAD_ENV", "test")
          (warn "Starting Iad server")
          (iad/start)))

(def events (atom []))

(Given #"^the conference is still far away in the future$" []
  (db/migrate))

(When #"^I request the list of the events$" []
  (let [raw-json-events ((client/get "http://localhost:8888/") :body)
        json-events (json/parse-string raw-json-events)
        event-count (count json-events)]
    (if-not (= 0 event-count) (swap! events #(apply conj % json-events)))))
    
(Then #"^the list of events is empty$" []
      (let [howmany (count @events)
            msg (str howmany " expected and got " howmany " instead")]
        (assert (= howmany 0) msg)))

(After [] (do
            (warn "Stopping iad embedded Jetty")
            (iad/stop)))
