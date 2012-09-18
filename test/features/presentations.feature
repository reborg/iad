Feature: Event presentations
  The IAD rest server can list all the presentations for a given event.


  Scenario: the key note was confirmed and there is an event
    Given the keynote was confirmed
    When I request the list of presentations of the event "1"
    Then there is "1" presentation