(ns leindb
  (:require [iad.db :as db]) 
  (:require [iad.seed :as seed]))

(defn migrate []
  (db/migrate))

(defn drop-tables []
  (db/drop-tables))

(defn seed []
  (seed/all))
