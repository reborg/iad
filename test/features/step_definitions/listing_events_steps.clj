(require '[iad.core :as iad])
(require '[clj-http.client :as client])
(require '[clj-json.core :as json])
(require '[clojure.java.jdbc :as sql]) 
(use '[clojure.tools.logging :only [info warn error]])

(Before [] 
        (do
          (warn "Starting Iad server")
          (iad/start)))

(def events (atom []))

(Given #"^the conference is still far away in the future$" []
       (let [iad-test {:classname   "org.h2.Driver"
                       :subprotocol "h2:mem"
                       :subname     "test;DB_CLOSE_DELAY=-1"
                       :user        "sa"
                       :password    ""}]
         (sql/with-connection iad-test 
           (sql/create-table :EVENT
                         [:id "identity"]
                         [:title "varchar(255)"]
                         [:author "varchar(90)"]
                         [:schedule "timestamp"]
                         [:length "int"]
                         [:active "boolean"]
                         [:abstract "varchar(600)"]))))
;           (sql/insert-records :event
;              {:title "agile development with clojure" 
;               :author "ramborg" 
;               :schedule "2012-11-24 09:30:00"
;               :length "45"
;               :active true
;               :abstract "why clojure should be agile?"}))))

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
