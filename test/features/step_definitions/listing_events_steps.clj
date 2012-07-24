(use 'iad.core)

(Given #"^the conference is still far away in the future$" []
       (start))

(When #"^I request the list of the events$" []
  (comment rest call))

(Then #"^the list of events is empty$" []
      (assert (= "same" "same")))
