Feature: Event presentations
  The IAD rest server can list all the presentations for a given event

  Scenario: schedule is finalized
    Given the "iad" event schedule was finalized
    And the talk "better agile less fragile" was confirmed for "iad"
    And the talk "introducting agile" was confirmed for "iad"
    When I request the list of presentations for "iad"
    Then there are "2" presentations
