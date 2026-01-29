Feature: Search Breweries

  Scenario: Partial match returns brewery with matching name
    When I search breweries with query "Deft"
    Then response contains at least one brewery with name containing "Deft"

  Scenario: Search is case-insensitive
    When I search breweries with query "dEfT"
    Then response contains at least one brewery with name containing "Deft"

  Scenario: Parameter per_page limits number of results
    When I search breweries with query "Brew" per_page 2 page 1
    Then response size is 2

  Scenario Outline: Query parameter is required
    When I search breweries "<queryMode>"
#  need requirement clarification on expected status code and message to validate fully
    Then response status should not be 200

    Examples:
      | queryMode        |
      | without query    |
      | with empty query |

  Scenario: query with no matches returns empty array
    When I search breweries with query "noSuch-brewery-xyz-12345"
    Then the response is empty