(ns iad.test-helper)
(System/setProperty "IAD_ENV", "test")
(use '[iad.config.environment])

(defn test-setup []
  (env-setup))
