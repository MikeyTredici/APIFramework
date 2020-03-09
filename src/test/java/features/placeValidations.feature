Feature: Validating Place APIs

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceApi
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with "post" http request
    Then The API call succeeds with a status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address            |
      | House A | English  | World cross center |
#      | House B | French   | Biosphere          |

  @DeletePlace @Regression
  Scenario: Verify if Delete place functionality is working
    Given Delete Place Payload
    When User calls "deletePlaceAPI" with "post" http request
    Then The API call succeeds with a status code 200
    And "status" in response body is "OK"


