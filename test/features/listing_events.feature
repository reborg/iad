Feature: Listing events
  The IAD rest server can list all the scheduled events,
  including a few filters. Only active events are shown
  by default.

  Scenario: there are no events
    Given the conference is still far away in the future
    When I request the list of the events
    Then the list of events is empty
