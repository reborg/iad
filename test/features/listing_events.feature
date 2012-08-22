Feature: Listing events
  The IAD rest server can list all the scheduled events,
  including a few filters. Only active events are shown
  by default.

  Scenario: there are no events
    Given the conference is still far away in the future
    When I request the list of the events
    Then the list of events is empty

  Scenario: the key note was confirmed and there is an event
    Given the keynote was confirmed
    When I request the list of the events
    Then there is "1" event
