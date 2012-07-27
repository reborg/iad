(require '[iad.core :as iad])
(require '[clj-http.client :as client])
(require '[clj-json.core :as json])

(Before [] 
        (do
          (println "Starting iad embedded Jetty")
          (iad/start)))

(def events (atom {}))

(Given #"^the conference is still far away in the future$" []
       (comment initialize empty DB))

(When #"^I request the list of the events$" []
  (let [all-events (client/get "http://localhost:8888/")]
      (swap! events conj (json/parse-string (all-events :body)))))
    
(Then #"^the list of events is empty$" []
      (assert (= (count @events) 0)))

(After [] 
        (do
          (println "\n\nStopping iad embedded Jetty")
          (iad/stop)))
