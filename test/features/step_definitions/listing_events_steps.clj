(require '[iad.core :as iad])
(require '[clj-http.client :as client])
(require '[clj-json.core :as json])
(require '[clojure.java.jdbc :as sql]) 

(Before [] 
        (do
          (println "Starting iad embedded Jetty")
          (iad/start)
          (let [iad-test {:classname   "org.h2.Driver"
                          :subprotocol "h2:file"
                          :subname     (str (System/getProperty "user.dir") "/db/" "iad-test")
                          :user        "sa"
                          :password    ""}]
            (sql/with-connection iad-test 
              (sql/drop-table :EVENT)))))

(def events (atom {}))

(Given #"^the conference is still far away in the future$" []
       (let [iad-test {:classname   "org.h2.Driver"
                      :subprotocol "h2:file"
                      :subname     (str (System/getProperty "user.dir") "/db/" "iad-test")
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
                         [:abstract "clob"])
           (sql/insert-records :event
              {:title "agile development with clojure" 
               :author "ramborg" 
               :schedule "2012-11-24 09:30:00"
               :length "45"
               :active true
               :abstract "why clojure should be agile?"}))))

(When #"^I request the list of the events$" []
  (let [all-events (client/get "http://localhost:8888/")]
      (swap! events conj (json/parse-string (all-events :body)))))
    
(Then #"^the list of events is empty$" []
      (assert (= (count @events) 0)))

(After [] 
        (do
          (println "\n\nStopping iad embedded Jetty")
          (iad/stop)))
