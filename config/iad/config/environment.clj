(ns iad.config.environment)

(defn env-setup
  "Setup the IAD server environment"
  []
  (str "iad env is " (. System getProperty "IAD_ENV")))
