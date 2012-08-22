(ns iad.config.environment)

(def dev-db
  {:classname   "org.h2.Driver"
   :subprotocol "h2:mem"
   :subname     "dev;DB_CLOSE_DELAY=-1"
   :user        "sa"
   :password    ""})

(def test-db
  {:classname   "org.h2.Driver"
   :subprotocol "h2:mem"
   :subname     "test;DB_CLOSE_DELAY=-1"
   :user        "sa"
   :password    ""})

(def prod-db
  {:classname   "org.h2.Driver"
   :subprotocol "h2:mem"
   :subname     "prod;DB_CLOSE_DELAY=-1"
   :user        "sa"
   :password    ""})
